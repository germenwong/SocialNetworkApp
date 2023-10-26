package com.hgm.socialnetworktwitch.feature_activity.domain.model


data class Activity(
      val userId: String,
      val parentId: String,
      val username: String,
      val activityType: ActivityType,
      val formattedTime: String
)
