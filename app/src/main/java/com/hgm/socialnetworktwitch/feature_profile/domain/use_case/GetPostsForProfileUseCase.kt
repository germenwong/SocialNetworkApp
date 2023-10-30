package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.repository.ProfileRepository
import com.hgm.socialnetworktwitch.core.util.Resource

/**
 * @auth：HGM
 * @date：2023-10-24 17:22
 * @desc：获取自己的所有帖子（自定义分页）
 */
class GetPostsForProfileUseCase(
      private val repository: ProfileRepository
) {

      suspend operator fun invoke(page: Int, userId: String): Resource<List<Post>> {
            return repository.getPostsPaged(
                  page = page,
                  userId = userId
            )
      }
}