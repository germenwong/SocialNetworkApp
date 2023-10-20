package com.hgm.socialnetworktwitch.feature_post.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.google.gson.Gson
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.data.dto.CreatePostRequest
import com.hgm.socialnetworktwitch.feature_post.data.remote.PostApi
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException


class PostRepositoryImpl(
      private val api: PostApi,
      private val gson: Gson,
) : PostRepository {

      override suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>> {
            return try {
                  val posts = api.getPostsForFollows(
                        page = page,
                        pageSize = pageSize
                  )
                  Resource.Success(data = posts)
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


      override suspend fun createPost(description: String, imageUri: Uri): SimpleResource {
            val request = CreatePostRequest(description)
            val file=imageUri.toFile()

            return try {
                  val response = api.createPost(
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
}