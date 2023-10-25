package com.hgm.socialnetworktwitch.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components.ProfileToolbarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
      private val profileUseCases: ProfileUseCases,
      savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _toolbarState = mutableStateOf(ProfileToolbarState())
      val toolbarState: State<ProfileToolbarState> = _toolbarState

      private val _state = mutableStateOf(ProfileState())
      val state: State<ProfileState> = _state

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      val posts =
            profileUseCases.getPostsForProfileUseCase(
                  savedStateHandle.get<String>("userId") ?: ""
            ).cachedIn(viewModelScope)


      fun setExpandedRatio(ratio: Float) {
            _toolbarState.value = _toolbarState.value.copy(expandedRatio = ratio)
      }

      fun setToolbarOffsetY(value: Float) {
            _toolbarState.value = _toolbarState.value.copy(toolbarOffsetY = value)
      }

      fun getProfile(userId: String) {
            viewModelScope.launch {
                  _state.value = _state.value.copy(
                        isLoading = true
                  )
                  val result = profileUseCases.getProfileUseCase(userId)
                  when (result) {
                        is Resource.Success -> {
                              _state.value = _state.value.copy(
                                    profile = result.data,
                                    isLoading = false
                              )
                        }

                        is Resource.Error -> {
                              _state.value = _state.value.copy(
                                    isLoading = false
                              )
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }

                  _state.value = _state.value.copy(
                        isLoading = false
                  )
            }
      }

}