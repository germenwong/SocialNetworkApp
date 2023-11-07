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
      private val chatUseCases: ChatUseCases,
      private val savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _state = mutableStateOf(MessageState())
      val state: State<MessageState> = _state

      //输入框状态
      private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
      val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState

      private val _pagingState = mutableStateOf<PagingState<Message>>(PagingState())
      val pagingState: State<PagingState<Message>> = _pagingState

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      private val paginator = DefaultPaginator(
            onLoading = { isLoading ->
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
            }, onError = { uiText ->
                  _eventFlow.emit(
                        UiEvent.ShowSnackBar(uiText)
                  )
            })


      init {
            loadNextMessages()
            observeChatEvents()
      }

      private fun observeChatMessage() {
            chatUseCases.receiveMessage()
                  .onEach { message ->
                        _state.value = state.value.copy(
                              messages = state.value.messages + message
                        )
                  }.launchIn(viewModelScope)
      }

      private fun observeChatEvents() {
            chatUseCases.observeChatEvents()
                  .onEach { event ->
                        when (event) {
                              is WebSocket.Event.OnConnectionOpened<*> -> {
                                    Log.d("WebSocketEvent", "连接成功")
                                    observeChatMessage()
                              }

                              is WebSocket.Event.OnMessageReceived -> TODO()
                              is WebSocket.Event.OnConnectionClosing -> TODO()
                              is WebSocket.Event.OnConnectionClosed -> TODO()
                              is WebSocket.Event.OnConnectionFailed -> {
                                    Log.d("WebSocketEvent", "连接失败  原因：${event.throwable.localizedMessage}")
                              }
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
                  receiveId = receiveId,
                  text = messageTextFieldState.value.text,
                  chatId = chatId
            )
      }

      fun onEvent(event: MessageEvent) {
            when (event) {
                  is MessageEvent.EnterMessage -> {
                        _messageTextFieldState.value = messageTextFieldState.value.copy(
                              text = event.message
                        )
                  }

                  is MessageEvent.SendMessage -> {
                        sendMessage()
                  }
            }
      }
}