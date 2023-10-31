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
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.DefaultPaginator
import com.hgm.socialnetworktwitch.core.util.PostLiker
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
      private val postLiker: PostLiker,
      private val postUseCases: PostUseCases,
      private val profileUseCases: ProfileUseCases,
      private val savedStateHandle: SavedStateHandle,
      private val getOwnUserIdUseCase: GetOwnUserIdUseCase
) : ViewModel() {
      private val _state = mutableStateOf(ProfileState())
      val state: State<ProfileState> = _state

      private val _toolbarState = mutableStateOf(ProfileToolbarState())
      val toolbarState: State<ProfileToolbarState> = _toolbarState

      private val _eventFlow = MutableSharedFlow<Event>()
      val eventFlow = _eventFlow.asSharedFlow()

      private val _pagingState = mutableStateOf<PagingState<Post>>(PagingState())
      val pagingState: State<PagingState<Post>> = _pagingState


      private val paginator = DefaultPaginator(onLoading = { isLoading ->
            _pagingState.value = _pagingState.value.copy(
                  isLoading = isLoading
            )
      }, onRequest = { page ->
            val userId = savedStateHandle.get<String>("userId") ?: getOwnUserIdUseCase()
            profileUseCases.getPostsForProfileUseCase(
                  page = page, userId = userId
            )
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
                  is ProfileEvent.Logout-> {
                        profileUseCases.logoutUseCase()
                  }
                  is ProfileEvent.LikePost -> {
                        updateLikeState(parentId = event.postId)
                  }
                  is ProfileEvent.ShowLogoutDialog->{
                        _state.value=state.value.copy(
                              isShowLogoutDialog = true
                        )
                  }
                  is ProfileEvent.DismissLogoutDialog->{
                        _state.value=state.value.copy(
                              isShowLogoutDialog = false
                        )
                  }
            }
      }

      fun loadNextPosts() {
            viewModelScope.launch {
                  paginator.loadNextItems()
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
                                    profile = result.data, isLoading = false
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

      private fun updateLikeState(parentId: String) {
            viewModelScope.launch {
                  postLiker.updateLikeState(posts = pagingState.value.items,
                        parentId = parentId,
                        onRequest = { isLiked ->
                              postUseCases.updateLikeParentUseCase(
                                    parentId = parentId,
                                    parentType = ParentType.Post.type,
                                    isLiked = isLiked
                              )
                        },
                        onStateUpdated = { posts ->
                              _pagingState.value = pagingState.value.copy(
                                    items = posts
                              )
                        }
                  )
            }
      }
}