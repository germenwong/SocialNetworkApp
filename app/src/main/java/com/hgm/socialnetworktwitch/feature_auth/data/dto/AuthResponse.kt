package com.hgm.socialnetworktwitch.feature_auth.data.dto

data class AuthResponse(
    val successful: Boolean,
    val message: String? = null,
    val token: String
)
