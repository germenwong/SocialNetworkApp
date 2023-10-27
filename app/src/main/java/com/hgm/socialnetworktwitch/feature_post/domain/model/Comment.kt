package com.hgm.socialnetworktwitch.feature_post.domain.model


data class Comment(
      val id: String,
      val profilePictureUrl: String,
      val username: String,
      val formattedTime: String,
      val comment: String,
      val likeCount: Int,
      val isLiked: Boolean
)
