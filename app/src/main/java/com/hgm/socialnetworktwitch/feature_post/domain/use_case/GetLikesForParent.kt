package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-10-29 13:17
 * @desc：获取父项点赞的用户列表
 */
class GetLikesForParent(
      private val repository: PostRepository
) {

      suspend operator fun invoke(parentId: String): Resource<List<UserItem>> {
            return repository.getLikesForParent(parentId)
      }
}