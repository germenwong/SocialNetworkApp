package com.hgm.socialnetworktwitch.feature_profile.util

import com.hgm.socialnetworktwitch.core.domain.model.Error


sealed class EditProfileError:Error(){
      object FieldEmpty : EditProfileError()
}
