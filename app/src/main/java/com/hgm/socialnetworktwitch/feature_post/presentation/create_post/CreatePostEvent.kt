package com.hgm.socialnetworktwitch.feature_post.presentation.create_post

import android.net.Uri

/**
 * @auth：HGM
 * @date：2023-10-19 19:00
 * @desc：创建帖子的事件
 */
sealed class CreatePostEvent {
      data class EnterDescription(val value: String) : CreatePostEvent()
      data class PickImage(val uri: Uri?) : CreatePostEvent()
      data class CropImage(val uri: Uri?) : CreatePostEvent()
      object Post : CreatePostEvent()
}
