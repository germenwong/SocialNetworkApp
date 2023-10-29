package com.hgm.socialnetworktwitch.feature_post.data.remote

import com.hgm.socialnetworktwitch.core.data.dto.BaseResponse
import com.hgm.socialnetworktwitch.core.data.dto.UserItemDto
import com.hgm.socialnetworktwitch.feature_post.data.dto.CommentDto
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.data.dto.AddCommentRequest
import com.hgm.socialnetworktwitch.feature_post.data.dto.LikeUpdateRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface PostApi {

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
            @Part postData: MultipartBody.Part,
            @Part postImage: MultipartBody.Part
      ): BaseResponse<Unit>

      @GET("/api/post/detail")
      suspend fun getPostDetail(
            @Query("postId") postId: String
      ): BaseResponse<Post>

      @GET("/api/comment/get")
      suspend fun getCommentForPost(
            @Query("postId") postId: String
      ): List<CommentDto>


      @POST("/api/comment/add")
      suspend fun addComment(
            @Body request:AddCommentRequest
      ):BaseResponse<Unit>


      @POST("/api/like/likes")
      suspend fun likeParent(
            @Body request: LikeUpdateRequest
      ): BaseResponse<Unit>

      @DELETE("/api/like/unlike")
      suspend fun unlikeParent(
            @Query("parentId") parentId: String,
            @Query("parentType") parentType: Int
      ): BaseResponse<Unit>


      @GET("/api/like/parent")
      suspend fun getLikesForParent(
            @Query("parentId") parentId:String
      ):List<UserItemDto>
}