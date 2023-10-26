package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

import com.hgm.socialnetworktwitch.core.domain.model.Post

/**
 * @auth：HGM
 * @date：2023-10-19 16:15
 * @desc：
 */
data class MainFeedState(
      val posts: List<Post> = emptyList(),
      val isLoading: Boolean = false,
      val page: Int = 0
)
