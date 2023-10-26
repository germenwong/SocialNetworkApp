package com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.hgm.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
      private val profileUseCases: ProfileUseCases,
      savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _usernameState = mutableStateOf(StandardTextFieldState())
      val usernameState: State<StandardTextFieldState> = _usernameState

      private val _bioState = mutableStateOf(StandardTextFieldState())
      val bioState: State<StandardTextFieldState> = _bioState

      private val _githubUrlState = mutableStateOf(StandardTextFieldState())
      val githubUrlState: State<StandardTextFieldState> = _githubUrlState

      private val _instagramUrlState = mutableStateOf(StandardTextFieldState())
      val instagramUrlState: State<StandardTextFieldState> = _instagramUrlState

      private val _linkedInUrlState = mutableStateOf(StandardTextFieldState())
      val linkedInUrlState: State<StandardTextFieldState> = _linkedInUrlState

      private val _skills = mutableStateOf(SkillsState())
      val skills: State<SkillsState> = _skills

      private val _bannerPictureUri = mutableStateOf<Uri?>(null)
      val bannerPictureUri: State<Uri?> = _bannerPictureUri

      private val _profilePictureUri = mutableStateOf<Uri?>(null)
      val profilePictureUri: State<Uri?> = _profilePictureUri

      private val _state = mutableStateOf(ProfileState())
      val state: State<ProfileState> = _state

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()


      init {
            savedStateHandle.get<String>("userId")?.let { userId ->
                  getSkills()
                  getProfile(userId)
            }
      }

      private fun getProfile(userId: String) {
            viewModelScope.launch {
                  when (val result = profileUseCases.getProfileUseCase(userId)) {
                        is Resource.Success -> {
                              val profile = result.data ?: kotlin.run {
                                    _eventFlow.emit(
                                          UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_couldnt_load_profile))
                                    )
                                    return@launch
                              }
                              //填充所有信息
                              _usernameState.value = _usernameState.value.copy(
                                    text = profile.username
                              )
                              _bioState.value = _bioState.value.copy(
                                    text = profile.bio
                              )
                              _githubUrlState.value = _githubUrlState.value.copy(
                                    text = profile.gitHubUrl ?: ""
                              )
                              _instagramUrlState.value = _instagramUrlState.value.copy(
                                    text = profile.instagramUrl ?: ""
                              )
                              _linkedInUrlState.value = _linkedInUrlState.value.copy(
                                    text = profile.linkedInUrl ?: ""
                              )
                              _skills.value = _skills.value.copy(
                                    selectedSkills = profile.topSkills
                              )
                              _state.value = _state.value.copy(
                                    profile = profile
                              )
                        }

                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }

      private fun getSkills() {
            viewModelScope.launch {
                  when (val result = profileUseCases.getSkillsUseCase()) {
                        is Resource.Success -> {
                              _skills.value = _skills.value.copy(
                                    skills = result.data ?: kotlin.run {
                                          _eventFlow.emit(
                                                UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_couldnt_load_skills))
                                          )
                                          return@launch
                                    }
                              )
                        }

                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_couldnt_load_skills))
                              )
                              return@launch
                        }
                  }
            }
      }

      private fun updateProfile() {
            viewModelScope.launch {
                  val result = profileUseCases.updateProfileUseCase(
                        bannerPictureUri = bannerPictureUri.value,
                        profilePictureUri = profilePictureUri.value,
                        updateProfileData = UpdateProfileData(
                              username = usernameState.value.text,
                              bio = bioState.value.text,
                              githubUrl = githubUrlState.value.text,
                              instagramUrl = instagramUrlState.value.text,
                              linkedInUrl = linkedInUrlState.value.text,
                              skills = skills.value.selectedSkills,
                        )
                  )
                  when (result) {
                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          uiText = result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }

                        is Resource.Success -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          uiText = UiText.StringResource(R.string.update_profile_successful)
                                    )
                              )
                              _eventFlow.emit(UiEvent.NavigateUp)
                        }
                  }
            }
      }


      fun onEvent(event: EditProfileEvent) {
            when (event) {
                  is EditProfileEvent.EnteredBio -> {
                        _bioState.value = _bioState.value.copy(
                              text = event.value
                        )
                  }

                  is EditProfileEvent.EnteredGitHubUrl -> {
                        _githubUrlState.value = _githubUrlState.value.copy(
                              text = event.value
                        )
                  }

                  is EditProfileEvent.EnteredInstagramUrl -> {
                        _instagramUrlState.value = _instagramUrlState.value.copy(
                              text = event.value
                        )
                  }

                  is EditProfileEvent.EnteredLinkedInUrl -> {
                        _linkedInUrlState.value = _linkedInUrlState.value.copy(
                              text = event.value
                        )
                  }

                  is EditProfileEvent.EnteredUsername -> {
                        _usernameState.value = _usernameState.value.copy(
                              text = event.value
                        )
                  }

                  is EditProfileEvent.CropBannerPicture -> {
                        _bannerPictureUri.value = event.uri
                  }

                  is EditProfileEvent.CropProfilePicture -> {
                        _profilePictureUri.value = event.uri
                  }

                  is EditProfileEvent.UpdateProfile -> {
                        updateProfile()
                  }

                  is EditProfileEvent.SetSkillSelected -> {
                        val result = profileUseCases.setSkillSelectedUseCase(
                              selectedSkills = skills.value.selectedSkills,
                              skillToToggle = event.skill
                        )
                        viewModelScope.launch {
                              when (result) {
                                    is Resource.Error -> {
                                          _eventFlow.emit(
                                                UiEvent.ShowSnackBar(
                                                      uiText = result.uiText
                                                            ?: UiText.StringResource(R.string.error_unknown)
                                                )
                                          )
                                    }

                                    is Resource.Success -> {
                                          _skills.value = _skills.value.copy(
                                                selectedSkills = result.data ?: kotlin.run {
                                                      _eventFlow.emit(
                                                            UiEvent.ShowSnackBar(
                                                                  UiText.StringResource(
                                                                        R.string.error_unknown
                                                                  )
                                                            )
                                                      )
                                                      return@launch
                                                }
                                          )
                                    }
                              }
                        }
                  }
            }
      }
}