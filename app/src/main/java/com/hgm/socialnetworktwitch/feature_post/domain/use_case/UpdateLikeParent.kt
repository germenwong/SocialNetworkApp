package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-10-25 16:22
 * @desc：更新点赞的用例
 */
class UpdateLikeParent(
      private val repository: PostRepository
) {
      suspend operator fun invoke(
            parentId: String,
            parentType: Int,
            isLiked: Boolean
      ): SimpleResource {
            return if(isLiked) {
                  repository.unlikeParent(parentId, parentType)
            } else {
                  repository.likeParent(parentId, parentType)
            }
      }
}