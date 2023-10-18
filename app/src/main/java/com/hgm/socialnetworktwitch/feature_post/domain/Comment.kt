package com.hgm.socialnetworktwitch.feature_post.domain

/**
 * @auth：HGM
 * @date：2023-10-11 11:15
 * @desc：
 */
data class Comment(
      val commentId: Int = -1,
      val profilePictureUrl: String = "",
      val username: String = "",
      val timestamp: Long = System.currentTimeMillis(),
      val comment: String = "",
      val likeCount: Int = 12,
      val isLiked:Boolean=false
)
