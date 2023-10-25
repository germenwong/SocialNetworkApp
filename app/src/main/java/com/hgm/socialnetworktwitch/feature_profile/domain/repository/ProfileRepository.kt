package com.hgm.socialnetworktwitch.feature_profile.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {

      suspend fun getProfile(userId: String): Resource<Profile>

       fun getPostsForProfile(userId: String): Flow<PagingData<Post>>

      suspend fun getSkills(): Resource<List<Skill>>

      suspend fun updateProfile(
            bannerPictureUri: Uri?,
            profilePictureUri: Uri?,
            updateProfileData: UpdateProfileData
      ): SimpleResource
}