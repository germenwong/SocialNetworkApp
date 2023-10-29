package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.hgm.socialnetworktwitch.feature_post.util.CommentError
import com.hgm.socialnetworktwitch.feature_post.util.ParentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(
      private val postUseCases: PostUseCases,
      private val savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _state = mutableStateOf(PostDetailState())
      val state: State<PostDetailState> = _state

      //评论输入框状态
      private val _commentTextState = mutableStateOf(StandardTextFieldState())
      val commentTextState: State<StandardTextFieldState> = _commentTextState

      //发表评论状态
      private val _commentState = mutableStateOf(CommentState())
      val commentState: State<CommentState> = _commentState

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      init {
            savedStateHandle.get<String>("postId")?.let { postId ->
                  loadPostDetail(postId)
                  loadCommentsForPost(postId)
            }
      }


      fun onEvent(event: PostDetailEvent) {
            when (event) {
                  is PostDetailEvent.LikePost -> {
                        val isLiked = state.value.post?.isLiked == true
                        updateLikeForParent(
                              parentId = state.value.post?.id ?: return,
                              parentType = ParentType.Post.type,
                              isLiked = isLiked
                        )
                  }
                  is PostDetailEvent.LikeComment -> {
                        val isLiked = state.value.comments.find {
                              it.id == event.commentId
                        }?.isLiked == true
                        updateLikeForParent(
                              parentId = event.commentId,
                              parentType = ParentType.Comment.type,
                              isLiked = isLiked
                        )
                  }
                  is PostDetailEvent.Comment -> {
                        addComment(
                              postId = savedStateHandle.get<String>("postId") ?: "",
                              comment = commentTextState.value.text
                        )
                  }
                  is PostDetailEvent.SharedPost -> TODO()
                  is PostDetailEvent.EnteredComment -> {
                        _commentTextState.value = commentTextState.value.copy(
                              text = event.comment,
                              error = if (event.comment.isBlank()) CommentError.FieldEmpty else null
                        )
                  }
            }
      }


      private fun loadPostDetail(postId: String) {
            viewModelScope.launch {
                  _state.value = state.value.copy(
                        isLoadingPost = true
                  )
                  when (val result = postUseCases.getPostDetailUseCase(postId)) {
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
                                          result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }

      private fun loadCommentsForPost(postId: String) {
            viewModelScope.launch {
                  _state.value = state.value.copy(
                        isLoadingComment = true
                  )
                  when (val result = postUseCases.getCommentForPostUseCase(postId)) {
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
                                          result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                        }
                  }
            }
      }

      private fun addComment(postId: String, comment: String) {
            viewModelScope.launch {
                  _commentState.value = commentState.value.copy(
                        isLoading = true
                  )

                  val result = postUseCases.addCommentUseCase(
                        postId = postId,
                        comment = comment
                  )

                  when (result) {
                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText ?: UiText.unknownError()
                                    )
                              )
                              _commentState.value = commentState.value.copy(
                                    isLoading = false
                              )
                        }

                        is Resource.Success -> {
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          UiText.StringResource(R.string.send_comment_successful)
                                    )
                              )
                              _commentState.value = commentState.value.copy(
                                    isLoading = false
                              )
                              _commentTextState.value = StandardTextFieldState()
                              loadCommentsForPost(postId)
                        }
                  }
            }
      }

      private fun updateLikeForParent(
            parentId: String,
            parentType: Int,
            isLiked: Boolean
      ) {
            viewModelScope.launch {
                  when(parentType) {
                        ParentType.Post.type -> {
                              _state.value = state.value.copy(
                                    post = state.value.post?.copy(
                                          isLiked = !isLiked,
                                    )
                              )
                        }
                        ParentType.Comment.type -> {
                              _state.value = state.value.copy(
                                    comments = state.value.comments.map { comment ->
                                          if(comment.id == parentId) {
                                                comment.copy(
                                                      isLiked = !isLiked
                                                )
                                          } else comment
                                    }
                              )
                        }
                  }
                  val result = postUseCases.updateLikeParentUseCase(
                        parentId = parentId,
                        parentType = parentType,
                        isLiked = isLiked
                  )
                  when(result) {
                        is Resource.Success -> Unit
                        is Resource.Error -> {
                              when(parentType) {
                                    ParentType.Post.type -> {
                                          _state.value = state.value.copy(
                                                post = state.value.post?.copy(
                                                      isLiked = isLiked,
                                                )
                                          )
                                    }
                                    ParentType.Comment.type -> {
                                          _state.value = state.value.copy(
                                                comments = state.value.comments.map { comment ->
                                                      if(comment.id == parentId) {
                                                            comment.copy(
                                                                  isLiked = isLiked
                                                            )
                                                      } else comment
                                                }
                                          )
                                    }
                              }
                        }
                  }
            }
      }
}