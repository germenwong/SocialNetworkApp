package com.hgm.socialnetworktwitch.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.use_case.GetOwnUserIdUseCase
import com.hgm.socialnetworktwitch.core.presentation.PagingState
import com.hgm.socialnetworktwitch.core.presentation.util.Event
import com.hgm.socialnetworktwitch.core.presentation.util.PostEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.hgm.socialnetworktwitch.feature_post.util.ParentType
import com.hgm.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components.ProfileToolbarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
      private val postUseCases: PostUseCases,
      private val profileUseCases: ProfileUseCases,
      private val getOwnUserIdUseCase: GetOwnUserIdUseCase,
      private val savedStateHandle: SavedStateHandle
) : ViewModel() {

      private val _toolbarState = mutableStateOf(ProfileToolbarState())
      val toolbarState: State<ProfileToolbarState> = _toolbarState

      private val _state = mutableStateOf(ProfileState())
      val state: State<ProfileState> = _state

      private val _eventFlow = MutableSharedFlow<Event>()
      val eventFlow = _eventFlow.asSharedFlow()

      private val _pagingState = mutableStateOf<PagingState<Post>>(PagingState())
      val pagingState: State<PagingState<Post>> = _pagingState

      private var page = 0

      init {
            loadNextPosts()
      }


      fun setExpandedRatio(ratio: Float) {
            _toolbarState.value = _toolbarState.value.copy(expandedRatio = ratio)
      }

      fun setToolbarOffsetY(value: Float) {
            _toolbarState.value = _toolbarState.value.copy(toolbarOffsetY = value)
      }

      fun onEvent(event: ProfileEvent) {
            when (event) {
                  is ProfileEvent.GetProfile -> TODO()
                  is ProfileEvent.LikePost -> {
                        //updateLikePost()
                  }
            }
      }

      fun loadNextPosts() {
            viewModelScope.launch {
                  _pagingState.value = _pagingState.value.copy(
                        isLoading = true
                  )
                  val userId = savedStateHandle.get<String>("userId") ?: getOwnUserIdUseCase()
                  val result = profileUseCases.getPostsForProfileUseCase(
                        page = page,
                        userId = userId
                  )
                  when (result) {
                        is Resource.Error -> {
                              _pagingState.value = pagingState.value.copy(
                                    isLoading = false,
                              )
                              _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                          result.uiText ?: UiText.unknownError()
                                    )
                              )
                        }

                        is Resource.Success -> {
                              //新一页的帖子
                              val posts = result.data ?: emptyList()
                              _pagingState.value = pagingState.value.copy(
                                    isLoading = false,
                                    items = pagingState.value.items + posts, //在原有帖子上加上新的帖子
                                    endReached = posts.isEmpty()
                              )
                              page++
                        }
                  }
            }
      }


      fun getProfile(userId: String?) {
            viewModelScope.launch {
                  _state.value = _state.value.copy(
                        isLoading = true
                  )
                  val result = profileUseCases.getProfileUseCase(
                        userId ?: getOwnUserIdUseCase()
                  )
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

      private fun updateLikePost(
            postId: String,
            isLiked: Boolean
      ) {
            viewModelScope.launch {
                  val result = postUseCases.updateLikeParentUseCase(
                        parentId = postId,
                        parentType = ParentType.Post.type,
                        isLiked = isLiked
                  )
                  when (result) {
                        is Resource.Success -> {
                              _eventFlow.emit(PostEvent.Refresh)
                        }

                        is Resource.Error -> {

                        }
                  }
            }
      }
}