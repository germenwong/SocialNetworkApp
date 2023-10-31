package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.repository.ProfileRepository

/**
 * @auth：HGM
 * @date：2023-10-31 9:35
 * @desc：退出登录
 */
class LogoutUseCase(
      private val repository: ProfileRepository
) {
      operator fun invoke(){
            return repository.logout()
      }
}