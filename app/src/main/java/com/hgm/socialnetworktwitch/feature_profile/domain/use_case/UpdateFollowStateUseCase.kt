package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

/**
 * @auth：HGM
 * @date：2023-10-25 16:22
 * @desc：更新关注状态用例
 */
class UpdateFollowStateUseCase(
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