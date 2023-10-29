package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hgm.socialnetworktwitch.core.presentation.util.Event
import com.hgm.socialnetworktwitch.core.presentation.util.PostEvent
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
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

      private val _state = mutableStateOf(MainFeedState())
      val stat: State<MainFeedState> = _state

      val posts =
            postUseCases.getPostsForFollowsUseCase().cachedIn(viewModelScope)

      private val _eventFlow = MutableSharedFlow<Event>()
      val eventFlow = _eventFlow.asSharedFlow()


      fun onEvent(event: MainFeedEvent) {
            when (event) {
                  is MainFeedEvent.LikePost -> {
                        //updateLikePost(postId = event.postId)
                  }
                  is MainFeedEvent.LoadedPage -> {
                        _state.value = stat.value.copy(
                              isLoadingFirstTime = false,
                              isLoadingNewPosts = false
                        )
                  }

                  is MainFeedEvent.LoadMorePosts -> {
                        _state.value = stat.value.copy(
                              isLoadingNewPosts = true
                        )
                  }
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
                  when(result) {
                        is Resource.Success -> {
                              _eventFlow.emit(PostEvent.Refresh)
                        }
                        is Resource.Error -> {

                        }
                  }
            }
      }

}