package com.hgm.socialnetworktwitch.core.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {

      suspend fun getProfile(userId: String): Resource<Profile>

      suspend fun getPostsPaged(
            page: Int = 0,
            pageSize: Int = Constants.PAGE_DEFAULT_SIZE,
            userId: String
      ): Resource<List<Post>>

      suspend fun getSkills(): Resource<List<Skill>>

      suspend fun updateProfile(
            bannerPictureUri: Uri?,
            profilePictureUri: Uri?,
            updateProfileData: UpdateProfileData
      ): SimpleResource

      suspend fun searchUser(query: String): Resource<List<UserItem>>

      suspend fun followUser(userId: String): SimpleResource

      suspend fun unfollowUser(userId: String): SimpleResource
}