package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.PagingState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.DefaultPaginator
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
      private val chatUseCases: ChatUseCases,
      private val savedStateHandle: SavedStateHandle
) : ViewModel() {

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
      }

       fun loadNextMessages() {
            viewModelScope.launch {
                  paginator.loadNextItems()
            }
      }

      fun onEvent(event: MessageEvent) {
            when (event) {
                  is MessageEvent.EnterMessage -> {
                        _messageTextFieldState.value = messageTextFieldState.value.copy(
                              text = event.message
                        )
                  }

                  is MessageEvent.SendMessage -> {

                  }
            }
      }
}