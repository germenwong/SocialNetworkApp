package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-10-24 17:22
 * @desc：
 */
class GetPostsForProfileUseCase(
      private val repository: ProfileRepository
) {

       operator fun invoke(userId: String): Flow<PagingData<Post>> {
            return repository.getPostsForProfile(userId)
      }
}