package com.hgm.socialnetworktwitch.feature_chat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
      private val chatUseCases: ChatUseCases
) : ViewModel() {

      private val _state = mutableStateOf(ChatState())
      val state: State<ChatState> = _state

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      init {
            loadChats()
      }

      private fun loadChats() {
            viewModelScope.launch {
                  _state.value = state.value.copy(isLoading = true)

                  when (val result = chatUseCases.getChatsForUser()) {
                        is Resource.Error -> {
                              _state.value = state.value.copy(isLoading = false)
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText ?: UiText.unknownError()
                                    )
                              )
                        }

                        is Resource.Success -> {
                              _state.value = state.value.copy(
                                    isLoading = false,
                                    chats = result.data ?: emptyList()
                              )
                        }
                  }
            }
      }
}