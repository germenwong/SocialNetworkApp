package com.hgm.socialnetworktwitch.feature_auth.presentation.register

/**
 * @auth：HGM
 * @date：2023-10-18 14:05
 * @desc：注册页面的操作事件
 */
sealed class RegisterEvent {
      data class EnteredUsername(val value: String) : RegisterEvent()
      data class EnteredEmail(val value: String) : RegisterEvent()
      data class EnteredPassword(val value: String) : RegisterEvent()
      object TogglePasswordVisibility : RegisterEvent()
      object Register : RegisterEvent()
}
