package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import android.net.Uri
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository
import com.hgm.socialnetworktwitch.feature_profile.util.ProfileConstants


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

            val isValidGithubUrl = updateProfileData.githubUrl.isBlank() ||
                    ProfileConstants.GITHUB_PROFILE_REGEX.matches(updateProfileData.githubUrl)
            if (!isValidGithubUrl) {
                  return Resource.Error(
                        uiText = UiText.StringResource(R.string.error_invalid_github_url)
                  )
            }

            val isValidInstagramUrl = updateProfileData.instagramUrl.isBlank() ||
                    ProfileConstants.INSTAGRAM_PROFILE_REGEX.matches(updateProfileData.instagramUrl)
            if (!isValidInstagramUrl) {
                  return Resource.Error(
                        uiText = UiText.StringResource(R.string.error_invalid_instagram_url)
                  )
            }

            val isValidLinkedUrl = updateProfileData.linkedInUrl.isBlank() ||
                    ProfileConstants.LINKED_IN_PROFILE_REGEX.matches(updateProfileData.linkedInUrl)
            if (!isValidLinkedUrl) {
                  return Resource.Error(
                        uiText = UiText.StringResource(R.string.error_invalid_linked_in_url)
                  )
            }

            return repository.updateProfile(
                  bannerPictureUri = bannerPictureUri,
                  profilePictureUri = profilePictureUri,
                  updateProfileData = updateProfileData
            )
      }
}