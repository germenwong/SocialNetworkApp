package com.hgm.socialnetworktwitch.feature_profile.domain.repository

import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile


interface ProfileRepository {

      suspend fun getProfile(userId:String):Resource<Profile>
}