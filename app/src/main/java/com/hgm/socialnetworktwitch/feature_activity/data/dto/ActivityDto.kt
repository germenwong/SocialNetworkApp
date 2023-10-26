package com.hgm.socialnetworktwitch.feature_activity.data.dto

import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import com.hgm.socialnetworktwitch.feature_activity.domain.model.ActivityType

data class ActivityDto(
      val timestamp: Long,
      val userId: String,
      val username: String,
      val parentId: String,
      val type: Int,
      val id: String
) {
      fun toActivity(): Activity {
            return Activity(
                  userId = userId,
                  parentId = parentId,
                  username = username,
                  activityType = when (type) {
                        ActivityType.LikedPost.type -> ActivityType.LikedPost
                        ActivityType.LikedComment.type -> ActivityType.LikedComment
                        ActivityType.CommentedOnPost.type -> ActivityType.CommentedOnPost
                        ActivityType.FollowedYou.type -> ActivityType.FollowedYou
                        else -> ActivityType.FollowedYou
                  },
                  formattedTime = DateFormattedUtil.timestampToString(
                        timestamp = timestamp,
                        pattern = "MMM dd HH:mm"
                  )
            )
      }
}
