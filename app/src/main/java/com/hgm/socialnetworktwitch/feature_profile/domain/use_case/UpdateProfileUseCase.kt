package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import android.net.Uri
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository


class UpdateProfileUseCase(
      private val repository: ProfileRepository
) {

      suspend operator fun invoke(
            bannerPictureUri: Uri?,
            profilePictureUri: Uri?,
            updateProfileData: UpdateProfileData
      ):SimpleResource{
            if (updateProfileData.username.isBlank()){
                  return Resource.Error(UiText.StringResource(R.string.username_cant_be_empty))
            }

            return repository.updateProfile(
                  bannerPictureUri = bannerPictureUri,
                  profilePictureUri = profilePictureUri,
                  updateProfileData = updateProfileData
            )
      }
}