package com.hgm.socialnetworktwitch.feature_profile.data.dto

import com.hgm.socialnetworktwitch.feature_profile.domain.model.UserItem


data class UserItemResponse(
      val userId: String,
      val username: String,
      val profilePictureUrl: String,
      val bio: String,
      val isFollowing: Boolean
) {
      fun toUserItem(): UserItem {
            return UserItem(
                  userId = userId,
                  username = username,
                  profilePictureUrl = profilePictureUrl,
                  bio = bio,
                  isFollowing = isFollowing
            )
      }
}