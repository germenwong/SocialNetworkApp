package com.hgm.socialnetworktwitch.core.domain.util

import android.util.Patterns
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_auth.util.AuthError

/**
 * @auth：HGM
 * @date：2023-10-19 10:24
 * @desc：验证工具类
 */
object ValidationUtil {

      fun validateEmail(email: String): AuthError? {
            val trimmedEmail = email.trim()

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                  return AuthError.InvalidEmail
            }
            if(trimmedEmail.isBlank()) {
                  return AuthError.FieldEmpty
            }
            return null
      }

      fun validateUsername(username: String): AuthError? {
            if (username.trim().isBlank()) {
                  return AuthError.FieldEmpty
            }

            if (username.trim().length < Constants.MIN_USERNAME_LENGTH) {
                  return AuthError.FieldTooShort
            }
            return null
      }

      fun validatePassword(password: String): AuthError? {
            if (password.trim().isBlank()) {
                  return AuthError.FieldEmpty
            }

            if (password.trim().length < Constants.MIN_PASSWORD_LENGTH) {
                  return AuthError.FieldTooShort
            }

            //密码是否包含大写、小写、数字三种字符
            val doesPwdContainChar =
                  password.any { it.isUpperCase() && it.isLowerCase() && it.isDigit() }
            if (!doesPwdContainChar) {
                  return AuthError.InvalidPassword
            }
            return null
      }
}