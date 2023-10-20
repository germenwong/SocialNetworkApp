package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainFeedViewModel @Inject constructor(
      private val postUseCases: PostUseCases
) : ViewModel() {

      private val _state = mutableStateOf(MainFeedState())
      val stat: State<MainFeedState> = _state


      fun onEvent(event: MainFeedEvent) {
            when (event) {
                  MainFeedEvent.LoadMorePosts -> {

                  }
            }
      }

}