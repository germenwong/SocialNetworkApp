package com.hgm.socialnetworktwitch.feature_auth.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_auth.data.dto.CreateAccountRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @auth：HGM
 * @date：2023-10-18 17:53
 * @desc：关于身份验证的接口
 */
interface AuthApi {

      companion object {
            const val BASE_URL = "http://10.0.2.2:8080"
      }

      //注册
      @POST("/api/user/register")
      suspend fun register(
            @Body request: CreateAccountRequest
      ): BaseResponse
}