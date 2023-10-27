package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.CreatePostUseCase
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(
      private val postUseCases: PostUseCases,
      savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _state = mutableStateOf(PostDetailState())
      val state: State<PostDetailState> = _state

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      init {
            savedStateHandle.get<String>("postId")?.let { postId ->
                  loadPostDetail(postId)
                  loadComments(postId)
            }
      }


      fun onEvent(event: PostDetailEvent) {
            when (event) {
                  PostDetailEvent.LikePost -> TODO()
                  PostDetailEvent.LikeComment -> TODO()
                  is PostDetailEvent.Comment -> TODO()
                  PostDetailEvent.SharedPost -> TODO()
            }
      }


      private fun loadPostDetail(postId: String) {
            viewModelScope.launch {
                  _state.value = state.value.copy(
                        isLoadingPost = true
                  )
                  when(val result = postUseCases.getPostDetailUseCase(postId)) {
                        is Resource.Success -> {
                              _state.value = state.value.copy(
                                    post = result.data,
                                    isLoadingPost = false
                              )
                        }
                        is Resource.Error -> {
                              _state.value = state.value.copy(
                                    isLoadingPost = false
                              )
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }


      private fun loadComments(postId: String) {
            viewModelScope.launch {
                  _state.value = state.value.copy(
                        isLoadingComment = true
                  )
                  when(val result = postUseCases.getCommentForPostUseCase(postId)) {
                        is Resource.Success -> {
                              _state.value = state.value.copy(
                                    comments = result.data ?: emptyList(),
                                    isLoadingComment = false
                              )
                        }
                        is Resource.Error -> {
                              _state.value = state.value.copy(
                                    isLoadingComment = false
                              )
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }

}