package com.hgm.socialnetworktwitch.feature_activity.domain.model

/**
 * @auth：HGM
 * @date：2023-10-11 14:25
 * @desc：用户的活动类型
 */
sealed class ActivityType(val type: Int) {
      object LikedPost : ActivityType(0)
      object LikedComment : ActivityType(1)
      object CommentedOnPost : ActivityType(2)
      object FollowedYou : ActivityType(3)
}
