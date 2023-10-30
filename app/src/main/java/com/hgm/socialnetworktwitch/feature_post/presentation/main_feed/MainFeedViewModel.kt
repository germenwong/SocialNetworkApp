package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.presentation.PagingState
import com.hgm.socialnetworktwitch.core.presentation.util.Event
import com.hgm.socialnetworktwitch.core.presentation.util.PostEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.DefaultPaginator
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.hgm.socialnetworktwitch.feature_post.util.ParentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainFeedViewModel @Inject constructor(
      private val postUseCases: PostUseCases
) : ViewModel() {

      private val _eventFlow = MutableSharedFlow<Event>()
      val eventFlow = _eventFlow.asSharedFlow()

      private val _pagingState = mutableStateOf<PagingState<Post>>(PagingState())
      val pagingState: State<PagingState<Post>> = _pagingState

      private val paginator = DefaultPaginator(
            onLoading = { isLoading ->
                  _pagingState.value = _pagingState.value.copy(
                        isLoading = isLoading
                  )
            },
            onRequest = { page ->
                  postUseCases.getPostsForFollowsUseCase(page)
            },
            onSuccess = { newPosts ->
                  _pagingState.value = pagingState.value.copy(
                        isLoading = false,
                        endReached = newPosts.isEmpty(),
                        items = pagingState.value.items + newPosts //在原有帖子上加上新的帖子
                  )
            },
            onError = { uiText ->
                  _eventFlow.emit(UiEvent.ShowSnackBar(uiText))
            }
      )

      init {
            loadNextItems()
      }

      fun onEvent(event: MainFeedEvent) {
            when (event) {
                  is MainFeedEvent.LikePost -> {
                        //updateLikePost(postId = event.postId)
                  }
            }
      }


      fun loadNextItems() {
            viewModelScope.launch {
                  paginator.loadNextItems()
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