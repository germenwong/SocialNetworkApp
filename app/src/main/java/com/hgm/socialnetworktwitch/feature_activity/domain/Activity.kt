package com.hgm.socialnetworktwitch.feature_activity.domain

import com.hgm.socialnetworktwitch.feature_activity.domain.ActivityAction

/**
 * @auth：HGM
 * @date：2023-10-11 14:28
 * @desc：
 */
data class Activity(
      val username:String,
      val actionType: ActivityAction,
      val formattedTime: String
)
