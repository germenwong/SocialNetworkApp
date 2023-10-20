package com.hgm.socialnetworktwitch.core.domain.model

/**
 * @auth：HGM
 * @date：2023-10-11 16:32
 * @desc：
 */
data class User(
      val profilePictureUrl: String,
      val username: String,
      val description: String,
      val followerCount: Int,
      val followingCount: Int,
      val postCount: Int,
)
