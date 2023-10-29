package com.hgm.socialnetworktwitch.core.presentation.util

/**
 * @auth：HGM
 * @date：2023-10-29 16:38
 * @desc：
 */
sealed class PostEvent : Event() {
      object Refresh : PostEvent()
}
