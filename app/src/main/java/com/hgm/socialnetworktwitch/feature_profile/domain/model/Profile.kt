package com.hgm.socialnetworktwitch.feature_profile.domain.model


data class Profile(
      val userId: String,
      val username: String,
      val bio: String,
      val followingCount: Int,
      val followedCount: Int,
      val postCount: Int,
      val profilePictureUrl: String,
      val bannerUrl: String?,
      val topSkills: List<Skill>,
      val gitHubUrl: String?,
      val instagramUrl: String?,
      val linkedInUrl: String?,
      val isOwnProfile: Boolean,
      val isFollowing: Boolean
)
