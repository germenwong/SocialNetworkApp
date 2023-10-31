package com.hgm.socialnetworktwitch.feature_auth.domain.use_case

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_auth.domain.repository.AuthRepository

/**
 * @auth：HGM
 * @date：2023-10-19 14:43
 * @desc：
 */
class AuthenticateUseCase(
      private val repository: AuthRepository
) {
      suspend operator fun invoke(): SimpleResource {
            return repository.authenticate()
      }
}