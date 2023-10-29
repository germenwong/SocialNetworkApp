package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile
import com.hgm.socialnetworktwitch.core.domain.repository.ProfileRepository


class GetProfileUseCase(
      private val repository: ProfileRepository
) {

      suspend operator fun invoke(userId: String): Resource<Profile> {
            return repository.getProfile(userId)
      }
}