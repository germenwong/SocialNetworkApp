package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
      private val chatUseCases: ChatUseCases
) : ViewModel() {

      //消息状态
      private val _state = mutableStateOf(MessageState())
      val state: State<MessageState> = _state

      //输入框状态
      private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
      val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()


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