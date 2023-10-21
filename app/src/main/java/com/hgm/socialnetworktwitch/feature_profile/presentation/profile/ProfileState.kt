package com.hgm.socialnetworktwitch.feature_profile.presentation.profile

import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile


data class ProfileState(
      val profile:Profile?=null,
      val isLoading:Boolean=false
)
