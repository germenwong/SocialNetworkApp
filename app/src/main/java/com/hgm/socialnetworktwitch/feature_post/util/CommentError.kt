package com.hgm.socialnetworktwitch.feature_post.util

import com.hgm.socialnetworktwitch.core.domain.model.Error

/**
 * @auth：HGM
 * @date：2023-10-28 10:34
 * @desc：
 */
sealed class CommentError : Error() {
      object FieldEmpty : CommentError()
}
