package com.hgm.socialnetworktwitch.feature_auth.presentation.login

/**
 * @auth：HGM
 * @date：2023-10-18 14:05
 * @desc：登录页面的操作事件
 */
sealed class LoginEvent {
      data class EnteredEmail(val value: String) : LoginEvent()
      data class EnteredPassword(val value: String) : LoginEvent()
      object TogglePasswordVisibility : LoginEvent()
      object Login : LoginEvent()
}
