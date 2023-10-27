package com.hgm.socialnetworktwitch.feature_auth.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_auth.data.dto.AuthResponse
import com.hgm.socialnetworktwitch.feature_auth.data.dto.CreateAccountRequest
import com.hgm.socialnetworktwitch.feature_auth.data.dto.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @auth：HGM
 * @date：2023-10-18 17:53
 * @desc：关于身份验证的接口
 */
interface AuthApi {

      @POST("/api/user/register")
      suspend fun register(
            @Body request: CreateAccountRequest
      ): BaseResponse<Unit>


      @POST("/api/user/login")
      suspend fun login(
            @Body request: LoginRequest
      ): BaseResponse<AuthResponse>


      @GET("/api/user/authenticate")
      suspend fun authenticate(): Response<Unit>
}