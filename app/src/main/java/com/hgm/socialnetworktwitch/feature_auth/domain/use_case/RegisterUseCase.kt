package com.hgm.socialnetworktwitch.feature_auth.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.util.ValidationUtil
import com.hgm.socialnetworktwitch.feature_auth.domain.model.RegisterResult
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
      ): RegisterResult {
            //在注册之前先本地验证字段，如果存在错误则返回错误即可
            val emailError = ValidationUtil.validateEmail(email)
            val usernameError = ValidationUtil.validateUsername(username)
            val passwordError = ValidationUtil.validatePassword(password)
            if (emailError != null || usernameError != null || passwordError != null) {
                  return RegisterResult(
                        emailError = emailError,
                        usernameError = usernameError,
                        passwordError = passwordError
                  )
            }

            //当所有字段验证成功后才开始网络请求，返回网络结果
            val result = repository.register(email.trim(), username.trim(), password.trim())
            return RegisterResult(
                  result = result
            )
      }
}