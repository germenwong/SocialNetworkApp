package com.hgm.socialnetworktwitch.feature_post.util

import com.hgm.socialnetworktwitch.core.domain.model.Error

/**
 * @auth：HGM
 * @date：2023-10-18 17:17
 * @desc：
 */
sealed class PostError:Error(){
      object FieldEmpty : PostError()
}
