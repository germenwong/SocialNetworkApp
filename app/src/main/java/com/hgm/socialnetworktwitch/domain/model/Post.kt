package com.hgm.socialnetworktwitch.domain.model

/**
 * @auth：HGM
 * @date：2023-10-10 11:02
 * @desc：
 */
data class Post(
      val username: String,
      val imageUrl: String,
      val profilePictureUrl: String,
      val description: String,
      val likeCount: Int,
      val commentCount: Int
)
