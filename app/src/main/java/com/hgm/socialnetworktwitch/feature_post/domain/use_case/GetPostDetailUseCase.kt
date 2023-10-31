package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-10-27 9:48
 * @desc：获取帖子详情
 */
class GetPostDetailUseCase(
      private val repository: PostRepository
) {
      suspend operator fun invoke(userId:String,postId: String): Resource<Post> {
            return repository.getPostDetail(userId,postId)
      }
}