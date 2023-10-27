package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment
import com.hgm.socialnetworktwitch.core.domain.model.Post


data class PostDetailState(
      val post: Post? = null,
      val comments: List<Comment> = emptyList(),
      val isLoadingPost: Boolean = false,
      val isLoadingComment: Boolean = false,
)
