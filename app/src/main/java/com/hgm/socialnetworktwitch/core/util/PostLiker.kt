package com.hgm.socialnetworktwitch.core.util

import com.hgm.socialnetworktwitch.core.domain.model.Post

/**
 * @auth：HGM
 * @date：2023-10-30 14:37
 * @desc：帖子点赞器
 */
interface PostLiker {

      suspend fun updateLikeState(
            posts: List<Post>,
            parentId: String,
            onRequest: suspend (isLiked:Boolean) -> SimpleResource,
            onStateUpdated: (List<Post>) -> Unit,
      )
}