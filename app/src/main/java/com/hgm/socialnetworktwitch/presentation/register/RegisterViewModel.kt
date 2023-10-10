package com.hgm.socialnetworktwitch.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @auth：HGM
 * @date：2023-09-22 15:54
 * @desc：
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {
      //用户名
      private val _usernameText = mutableStateOf("")
      val usernameText: State<String> = _usernameText
      //邮箱
      private val _emailText = mutableStateOf("")
      val emailText: State<String> = _emailText
      //密码
      private val _passwordText = mutableStateOf("")
      val passwordText: State<String> = _passwordText
      //密码可见
      private val _showPassword = mutableStateOf(false)
      val showPassword: State<Boolean> = _showPassword
      //用户名错误
      private val _usernameError = mutableStateOf("")
      val usernameError : State<String> = _usernameError
      //邮箱错误
      private val _emailError = mutableStateOf("")
      val emailError : State<String> = _emailError
      //密码错误
      private val _passwordError = mutableStateOf("")
      val passwordError: State<String> = _passwordError


      fun setPasswordText(password: String) {
            _passwordText.value = password
      }

      fun setEmailText(email: String) {
            _emailText.value = email
      }

      fun setUsernameText(username: String) {
            _usernameText.value = username
      }

      fun setShowPassword(showPassword: Boolean) {
            _showPassword.value = showPassword
      }

      fun setUsernameError(error: String) {
            _usernameError.value = error
      }

      fun setEmailError(error: String) {
            _emailError.value = error
      }

      fun setPasswordError(error: String) {
            _passwordError.value = error
      }


}