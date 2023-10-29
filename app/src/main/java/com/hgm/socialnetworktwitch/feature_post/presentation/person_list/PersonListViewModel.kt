package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.use_case.GetOwnUserIdUseCase
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.hgm.socialnetworktwitch.core.domain.use_case.UpdateFollowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonListViewModel @Inject constructor(
      private val postUseCases: PostUseCases,
      private val updateFollowUseCase: UpdateFollowUseCase,
      private val getOwnUserIdUseCase: GetOwnUserIdUseCase,
      savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _state = mutableStateOf(PersonListState())
      val state: State<PersonListState> = _state

      private val _ownUserId = mutableStateOf("")
      val ownUserId: State<String> = _ownUserId

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      init {
            savedStateHandle.get<String>("parentId")?.let { parentId ->
                  getLikesForParent(parentId)
                  _ownUserId.value = getOwnUserIdUseCase()
            }
      }

      fun onEvent(event: PersonListEvent) {
            when (event) {
                  is PersonListEvent.UpdateFollowState -> updateFollowState(event.userId)
            }
      }

      private fun getLikesForParent(parentId: String) {
            viewModelScope.launch {
                  _state.value = state.value.copy(isLoading = true)

                  when (val result = postUseCases.getLikesForParentUseCase(parentId)) {
                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(result.uiText ?: UiText.unknownError())
                              )
                              _state.value = state.value.copy(isLoading = false)
                        }

                        is Resource.Success -> {
                              _state.value = state.value.copy(
                                    users = result.data ?: emptyList(),
                                    isLoading = false
                              )
                        }
                  }
            }
      }


      private fun updateFollowState(userId: String) {
            viewModelScope.launch {
                  val isFollowing = state.value.users.find {
                        it.userId == userId
                  }?.isFollowing == true

                  _state.value = state.value.copy(
                        users = state.value.users.map {
                              if (it.userId == userId) {
                                    it.copy(isFollowing = !it.isFollowing)
                              } else it
                        }
                  )

                  val result = updateFollowUseCase(
                        userId = userId,
                        isFollowing = isFollowing
                  )
                  when (result) {
                        is Resource.Success -> Unit
                        is Resource.Error -> {
                              _state.value = state.value.copy(
                                    users = state.value.users.map {
                                          if (it.userId == userId) {
                                                it.copy(isFollowing = isFollowing)
                                          } else it
                                    }
                              )
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          uiText = result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }
}