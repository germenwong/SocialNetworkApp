package com.hgm.socialnetworktwitch.feature_auth.domain.use_case

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_auth.domain.repository.AuthRepository

/**
 * @auth：HGM
 * @date：2023-10-18 19:35
 * @desc：
 */
class RegisterUseCase(
      private val repository: AuthRepository
) {
      suspend operator fun invoke(
            email: String,
            username: String,
            password: String
      ): SimpleResource {
            return repository.register(email.trim(), username.trim(), password.trim())
      }
}