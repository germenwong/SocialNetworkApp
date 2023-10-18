package com.hgm.socialnetworktwitch.feature_auth.util

import com.hgm.socialnetworktwitch.core.domain.model.Error


/**
 * @auth：HGM
 * @date：2023-10-18 17:06
 * @desc：
 */
sealed class AuthError:Error(){
      object FieldEmpty : AuthError()
      object FieldTooShort : AuthError()
      object InvalidEmail : AuthError()
      object InvalidPassword : AuthError()
}
