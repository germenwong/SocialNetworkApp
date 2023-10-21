package com.hgm.socialnetworktwitch.feature_profile.domain.model

/**
 * @auth：HGM
 * @date：2023-10-20 18:01
 * @desc：
 */
data class UserItem(
      val userId: String,
      val username: String,
      val profilePictureUrl: String,
      val bio: String,
      val isFollowing: Boolean
)
