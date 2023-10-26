package com.hgm.socialnetworktwitch.feature_profile.data.repository

import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.data.remote.PostApi
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.data.paging.PostPagingSource
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.feature_profile.data.dto.FollowUpdateRequest
import com.hgm.socialnetworktwitch.feature_profile.data.remote.ProfileApi
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Profile
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException


class ProfileRepositoryImpl(
      private val gson: Gson,
      private val profileApi: ProfileApi,
      private val postApi: PostApi
) : ProfileRepository {
      override suspend fun getProfile(userId: String): Resource<Profile> {
            return try {
                  val response = profileApi.getProfile(userId)
                  if (response.successful) {
                        Resource.Success(response.data?.toProfile())
                  } else {
                        response.message?.let { msg ->
                              Resource.Error(UiText.DynamicString(msg))
                        } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
                  }
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override fun getPostsForProfile(userId: String): Flow<PagingData<Post>> {
            return Pager(
                  config = PagingConfig(pageSize = Constants.PAGE_DEFAULT_SIZE)
            ) {
                  PostPagingSource(postApi, PostPagingSource.Source.Profile(userId))
            }.flow
      }

      override suspend fun getSkills(): Resource<List<Skill>> {
            return try {
                  val response = profileApi.getSkills()
                  Resource.Success(response.map { it.toSkill() })
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override suspend fun updateProfile(
            bannerPictureUri: Uri?,
            profilePictureUri: Uri?,
            updateProfileData: UpdateProfileData
      ): SimpleResource {
            //先把uri转成file
            val bannerFile = bannerPictureUri?.toFile()
            val profileFile = profilePictureUri?.toFile()

            return try {
                  val response = profileApi.updateProfile(
                        bannerPicture = bannerFile?.let {
                              MultipartBody.Part
                                    .createFormData(
                                          "banner_picture",
                                          bannerFile.name,
                                          bannerFile.asRequestBody()
                                    )
                        },
                        profilePicture = profileFile?.let {
                              MultipartBody.Part
                                    .createFormData(
                                          "profile_picture",
                                          profileFile.name,
                                          profileFile.asRequestBody()
                                    )
                        },
                        updateProfileData = MultipartBody.Part
                              .createFormData(
                                    "update_profile_data",
                                    gson.toJson(updateProfileData)
                              )
                  )
                  if (response.successful) {
                        Resource.Success(Unit)
                  } else {
                        response.message?.let {
                              Resource.Error(UiText.DynamicString(it))
                        } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
                  }
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override suspend fun searchUser(query: String): Resource<List<UserItem>> {
            return try {
                  val userItems = profileApi.searchUser(query)
                  Resource.Success(userItems.map { it.toUserItem() })
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override suspend fun followUser(userId: String): SimpleResource {
            return try {
                  val request = FollowUpdateRequest(userId)
                  val response = profileApi.followUser(request)
                  if (response.successful) {
                        Resource.Success(Unit)
                  } else {
                        response.message?.let {
                              Resource.Error(
                                    uiText = UiText.DynamicString(it)
                              )
                        } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
                  }
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override suspend fun unfollowUser(userId: String): SimpleResource {
            return try {
                  val response = profileApi.unfollowUser(userId)
                  if (response.successful) {
                        Resource.Success(Unit)
                  } else {
                        response.message?.let {
                              Resource.Error(
                                    uiText = UiText.DynamicString(it)
                              )
                        } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
                  }
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }
}