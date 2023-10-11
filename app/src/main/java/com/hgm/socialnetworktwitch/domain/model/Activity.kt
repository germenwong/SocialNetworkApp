package com.hgm.socialnetworktwitch.domain.model

import com.hgm.socialnetworktwitch.domain.util.ActivityAction

/**
 * @auth：HGM
 * @date：2023-10-11 14:28
 * @desc：
 */
data class Activity(
      val username:String,
      val actionType:ActivityAction,
      val formattedTime: String
)
