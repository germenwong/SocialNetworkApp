package com.hgm.socialnetworktwitch.feature_profile.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.feature_profile.data.dto.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ProfileApi {

      companion object{
            const val BASE_URL="http://10.0.2.2:8080"
      }

      @GET("/api/user/profile")
      suspend fun getProfile(
            @Query("userId") userId:String
      ): BaseResponse<ProfileResponse>
}