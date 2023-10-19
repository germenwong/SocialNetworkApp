package com.hgm.socialnetworktwitch.feature_auth.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.util.ValidationUtil
import com.hgm.socialnetworktwitch.feature_auth.domain.model.LoginResult
import com.hgm.socialnetworktwitch.feature_auth.domain.model.RegisterResult
import com.hgm.socialnetworktwitch.feature_auth.domain.repository.AuthRepository
import com.hgm.socialnetworktwitch.feature_auth.util.AuthError

/**
 * @auth：HGM
 * @date：2023-10-18 19:35
 * @desc：登录用例
 */
class LoginUseCase(
      private val repository: AuthRepository,
) {
      suspend operator fun invoke(
            email: String, password: String
      ): LoginResult {
            val emailError = ValidationUtil.validateEmail(email)
            val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null
            if (emailError != null || passwordError != null) {
                  return LoginResult(
                        emailError = emailError, passwordError = passwordError
                  )
            }

            return LoginResult(
                  result = repository.login(email.trim(), password.trim())
            )
      }
}