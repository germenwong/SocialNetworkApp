package com.hgm.socialnetworktwitch.feature_auth.data.dto

/**
 * 用于接收用户发起注册的请求
 */
data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
