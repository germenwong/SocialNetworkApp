package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository


class SearchUserUseCase(
      private val repository: ProfileRepository
) {

      suspend operator fun invoke(query: String): Resource<List<UserItem>> {
            if (query.isBlank()) {
                  return Resource.Success(emptyList())
            }
            return repository.searchUser(query)
      }
}