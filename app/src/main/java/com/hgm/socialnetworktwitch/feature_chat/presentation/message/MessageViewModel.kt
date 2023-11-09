package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.PagingState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.DefaultPaginator
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
      private val chatUseCases: ChatUseCases, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

      //发送按钮状态
      private val _buttonState = mutableStateOf(false)
      val buttonState: State<Boolean> = _buttonState

      //输入框状态
      private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
      val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState

      //分页状态
      private val _pagingState = mutableStateOf<PagingState<Message>>(PagingState())
      val pagingState: State<PagingState<Message>> = _pagingState

      //事件流
      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      //接收信息状态
      private val _messageReceiver = MutableSharedFlow<MessageReceiverEvent>(replay = 1)
      val messageReceiver = _messageReceiver.asSharedFlow()

      private val paginator = DefaultPaginator(onLoading = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                  isLoading = isLoading
            )
      }, onRequest = { nextPage ->
            savedStateHandle.get<String>("chatId")?.let { chatId ->
                  chatUseCases.getMessagesForChat(chatId, nextPage)
            } ?: Resource.Error(UiText.unknownError())
      }, onSuccess = { newPosts ->
            _pagingState.value = pagingState.value.copy(
                  isLoading = false,
                  endReached = newPosts.isEmpty(),
                  items = pagingState.value.items + newPosts //在原有帖子上加上新的帖子
            )

            viewModelScope.launch { _messageReceiver.emit(MessageReceiverEvent.MessagePageLoaded) }
      }, onError = { uiText ->
            _eventFlow.emit(
                  UiEvent.ShowSnackBar(uiText)
            )
      })


      init {
            // 每次退出必须重新初始化对象，否则scarlet持有原来的导致WebSocket错误
            chatUseCases.initializeRepository()
            loadNextMessages()
            observeChatEvents()
            observeChatMessage()
      }

      private fun observeChatMessage() {
            viewModelScope.launch {
                  chatUseCases.receiveMessage().collect { message ->
                        Log.d("Receiver", "在MessageViewModel:接收到了消息：$message")
                        _pagingState.value = pagingState.value.copy(
                              items = pagingState.value.items + message
                        )
                        _messageReceiver.emit(MessageReceiverEvent.SingleMessageReceiver)
                  }
            }
      }

      private fun observeChatEvents() {
            chatUseCases.observeChatEvents().onEach { event ->
                  when (event) {
                        is WebSocket.Event.OnConnectionOpened<*> -> {
                              Log.d("WebSocketEvent", "连接成功")
                        }

                        is WebSocket.Event.OnConnectionFailed -> {
                              Log.d(
                                    "WebSocketEvent",
                                    "连接失败  原因：${event.throwable.localizedMessage}"
                              )
                        }

                        else -> Unit
                  }
            }.launchIn(viewModelScope)
      }

      fun loadNextMessages() {
            viewModelScope.launch {
                  paginator.loadNextItems()
            }
      }

      private fun sendMessage() {
            val receiveId = savedStateHandle.get<String>("remoteUserId") ?: return
            val chatId = savedStateHandle.get<String>("chatId")
            chatUseCases.sendMessage(
                  receiveId = receiveId, text = messageTextFieldState.value.text, chatId = chatId
            )
            _messageTextFieldState.value = StandardTextFieldState()
            _buttonState.value = false
      }

      fun onEvent(event: MessageEvent) {
            when (event) {
                  is MessageEvent.EnterMessage -> {
                        _messageTextFieldState.value = messageTextFieldState.value.copy(
                              text = event.message
                        )
                        _buttonState.value = event.message.isNotBlank()
                  }

                  is MessageEvent.SendMessage -> {
                        sendMessage()
                  }
            }
      }


      sealed class MessageReceiverEvent {
            object SingleMessageReceiver : MessageReceiverEvent()
            object MessagePageLoaded : MessageReceiverEvent()
      }
}