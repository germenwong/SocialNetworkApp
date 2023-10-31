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
            val capitalLettersInPassword = password.any { it.isUpperCase() }
            val numberInPassword = password.any { it.isDigit() }
            if(!capitalLettersInPassword || !numberInPassword) {
                  return AuthError.InvalidPassword
            }
            if(password.length < Constants.MIN_PASSWORD_LENGTH) {
                  return AuthError.FieldTooShort
            }
            if(password.isBlank()) {
                  return AuthError.FieldEmpty
            }
            return null
      }
}