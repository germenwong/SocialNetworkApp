package com.hgm.socialnetworktwitch.core.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.core.domain.model.Post
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * @auth：HGM
 * @date：2023-10-19 15:42
 * @desc：
 */
interface PostApi {

      companion object {
            const val BASE_URL = "http://192.168.31.164:8080"
      }

      @GET("/api/post/get")
      suspend fun getPostsForFollows(
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int
      ): List<Post>

      @GET("/api/user/post")
      suspend fun getPostsForProfile(
            @Query("userId") userId: String,
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int
      ): List<Post>


      @Multipart//多部分上传
      @POST("/api/post/create")
      suspend fun createPost(
            @Part postData:MultipartBody.Part,
            @Part postImage:MultipartBody.Part
      ): BaseResponse<Unit>
}