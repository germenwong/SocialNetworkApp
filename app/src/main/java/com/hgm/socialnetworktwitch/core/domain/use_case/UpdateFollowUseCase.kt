package com.hgm.socialnetworktwitch.core.domain.use_case

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.core.domain.repository.ProfileRepository

/**
 * @auth：HGM
 * @date：2023-10-25 16:22
 * @desc：更新关注状态用例
 */
class UpdateFollowUseCase(
      private val repository: ProfileRepository
) {
      suspend operator fun invoke(userId: String, isFollowing: Boolean): SimpleResource {
            return if (isFollowing) {
                  repository.unfollowUser(userId)
            } else {
                  repository.followUser(userId)
            }
      }
}