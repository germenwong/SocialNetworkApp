package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-10-19 16:09
 * @desc：获取关注列表中的人的所有帖子
 */
class GetPostsForFollowsUseCase(
      private val repository: PostRepository
) {

      suspend operator fun invoke(
            page: Int,
            pageSize: Int = Constants.PAGE_SIZE_POST
      ): Resource<List<Post>> {
            return repository.getPostsForFollows(page, pageSize)
      }
}