package com.hgm.socialnetworktwitch.feature_profile.domain.repository

import android.net.Uri
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData


interface ProfileRepository {

      suspend fun getProfile(userId: String): Resource<Profile>

      suspend fun getSkills(): Resource<List<Skill>>

      suspend fun updateProfile(
            bannerPictureUri: Uri?,
            profilePictureUri: Uri?,
            updateProfileData: UpdateProfileData
      ):SimpleResource
}