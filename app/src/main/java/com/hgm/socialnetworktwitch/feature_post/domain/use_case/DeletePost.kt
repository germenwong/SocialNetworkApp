package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-11-09 16:47
 * @desc：删除帖子
 */
class DeletePost(
      private val repository: PostRepository
) {
      suspend operator fun invoke(postId: String): SimpleResource {
            return repository.deletePost(postId)
      }
}