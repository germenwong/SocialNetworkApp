package com.hgm.socialnetworktwitch.feature_auth.presentation.register

/**
 * @auth：HGM
 * @date：2023-10-18 12:02
 * @desc：
 */
data class RegisterState(
      val usernameText: String = "",
      val usernameError: UsernameError? = null,
      val emailText: String = "",
      val emailError: EmailError? = null,
      val passwordText: String = "",
      val passwordError: PasswordError? = null,
      val isPasswordVisible: Boolean = false
) {
      sealed class UsernameError {
            object FieldEmpty : UsernameError()
            object FieldTooShort : UsernameError()
      }

      sealed class EmailError {
            object FieldEmpty : EmailError()
            object InvalidEmail : EmailError()
      }

      sealed class PasswordError {
            object FieldEmpty : PasswordError()
            object FieldTooShort : PasswordError()
            object InvalidPassword : PasswordError()
      }
}
