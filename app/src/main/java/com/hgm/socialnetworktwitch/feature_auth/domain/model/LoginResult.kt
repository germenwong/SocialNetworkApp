package com.hgm.socialnetworktwitch.feature_auth.domain.model

import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_auth.util.AuthError

/**
 * @auth：HGM
 * @date：2023-10-19 10:09
 * @desc：登录返回的错误
 */
data class LoginResult(
      val emailError: AuthError? = null,
      val passwordError: AuthError? = null,
      val result: SimpleResource? = null
)
