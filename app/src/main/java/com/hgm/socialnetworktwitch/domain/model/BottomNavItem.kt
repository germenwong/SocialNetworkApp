package com.hgm.socialnetworktwitch.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @auth：HGM
 * @date：2023-10-10 15:55
 * @desc：
 */
data class BottomNavItem(
      val route:String,
      val icon: ImageVector,
      val contentDescription: String,
      val alertCount: Int?=null
)
