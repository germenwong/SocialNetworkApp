package com.hgm.socialnetworktwitch.feature_profile.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.feature_profile.data.dto.ProfileResponse
import com.hgm.socialnetworktwitch.feature_profile.data.dto.SkillDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query


interface ProfileApi {

      companion object {
            const val BASE_URL = "http://10.0.2.2:8080"
      }

      @GET("/api/user/profile")
      suspend fun getProfile(
            @Query("userId") userId: String
      ): BaseResponse<ProfileResponse>

      @GET("/api/skills/get")
      suspend fun getSkills(): List<SkillDto>

      @Multipart
      @PUT("/api/user/update")
      suspend fun updateProfile(
            @Part bannerPicture: MultipartBody.Part?,
            @Part profilePicture: MultipartBody.Part?,
            @Part updateProfileData: MultipartBody.Part
      ):BaseResponse<Unit>
}