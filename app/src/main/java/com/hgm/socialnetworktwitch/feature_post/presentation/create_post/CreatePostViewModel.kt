package com.hgm.socialnetworktwitch.feature_post.presentation.create_post

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreatePostViewModel @Inject constructor(
      private val postUseCases: PostUseCases
) : ViewModel() {

      private val _descriptionState = mutableStateOf(StandardTextFieldState())
      val descriptionState: State<StandardTextFieldState> = _descriptionState

      private val _pickedImageUri = mutableStateOf<Uri?>(null)
      val pickedImageUri: State<Uri?> = _pickedImageUri

      private val _isLoading = mutableStateOf(false)
      val isLoading: State<Boolean> = _isLoading

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      fun onEvent(event: CreatePostEvent) {
            when (event) {
                  is CreatePostEvent.EnterDescription -> {
                        _descriptionState.value = _descriptionState.value.copy(
                              text = event.value
                        )
                  }

                  is CreatePostEvent.PickImage -> {
                        _pickedImageUri.value = event.uri
                  }

                  is CreatePostEvent.CropImage -> {
                        _pickedImageUri.value = event.uri
                  }

                  is CreatePostEvent.Post -> {
                        createPost()
                  }

            }
      }

      private fun createPost() {
            viewModelScope.launch {
                  _isLoading.value = true
                  val result = postUseCases.createPostUseCase(
                        description = descriptionState.value.text,
                        imageUri = pickedImageUri.value
                  )
                  when (result) {
                        is Resource.Error -> {
                              _eventFlow.emit(UiEvent.ShowSnackBar(
                                    uiText = result.uiText ?:   UiText.StringResource(R.string.error_unknown)
                              ))
                        }

                        is Resource.Success -> {
                              _eventFlow.emit(UiEvent.ShowSnackBar(
                                    uiText = UiText.StringResource(R.string.create_post_successful)
                              ))
                              _eventFlow.emit(UiEvent.NavigateUp)
                        }
                  }
                  _isLoading.value = false
            }
      }
}