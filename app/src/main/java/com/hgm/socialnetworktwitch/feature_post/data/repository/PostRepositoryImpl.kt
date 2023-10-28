package com.hgm.socialnetworktwitch.feature_post.data.repository

import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.data.dto.CreatePostRequest
import com.hgm.socialnetworktwitch.core.data.remote.PostApi
import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_post.data.paging.PostPagingSource
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.feature_post.data.dto.AddCommentRequest
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException


class PostRepositoryImpl(
      private val postApi: PostApi,
      private val gson: Gson,
) : PostRepository {

      override fun getPostsForFollows(): Flow<PagingData<Post>> {
            return Pager(
                  config = PagingConfig(pageSize = Constants.PAGE_DEFAULT_SIZE)
            ) {
                  PostPagingSource(postApi, PostPagingSource.Source.Follows)
            }.flow
      }


      override suspend fun createPost(description: String, imageUri: Uri): SimpleResource {
            val request = CreatePostRequest(description)
            val file = imageUri.toFile()

            return try {
                  val response = postApi.createPost(
                        postData = MultipartBody.Part.createFormData(
                              "post_data",
                              gson.toJson(request)
                        ),
                        postImage = MultipartBody.Part.createFormData(
                              "post_image",
                              filename = file.name,
                              body = file.asRequestBody()
                        ),
                  )
                  if (response.successful) {
                        Resource.Success(Unit)
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

      override suspend fun getPostDetail(postId: String): Resource<Post> {
            return try {
                  val response = postApi.getPostDetail(postId = postId)
                  if (response.successful) {
                        Resource.Success(response.data)
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

      override suspend fun getCommentForPost(postId: String): Resource<List<Comment>> {
            return try {
                  val comments = postApi.getCommentForPost(postId = postId).map { it.toComment() }
                  Resource.Success(comments)
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

      override suspend fun addComment(postId: String, comment: String): SimpleResource {
            return try {
                  val response = postApi.addComment(
                        AddCommentRequest(postId, comment)
                  )
                  if (response.successful) {
                        Resource.Success(response.data)
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
}