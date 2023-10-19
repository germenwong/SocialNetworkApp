package com.hgm.socialnetworktwitch.feature_auth.data.repository

import android.content.SharedPreferences
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.feature_auth.data.dto.CreateAccountRequest
import com.hgm.socialnetworktwitch.feature_auth.data.dto.LoginRequest
import com.hgm.socialnetworktwitch.feature_auth.data.remote.AuthApi
import com.hgm.socialnetworktwitch.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

/**
 * @auth：HGM
 * @date：2023-10-18 18:14
 * @desc：
 */
class AuthRepositoryImpl(
      private val api: AuthApi,
      private val sharedPreference: SharedPreferences
) : AuthRepository {

      override suspend fun register(
            email: String,
            username: String,
            password: String
      ): SimpleResource {
            return try {
                  val request = CreateAccountRequest(email, username, password)
                  val response = api.register(request)
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


      override suspend fun login(email: String, password: String): SimpleResource {
            return try {
                  val request = LoginRequest(email, password)
                  val response = api.login(request)
                  if (response.successful) {
                        //保存token
                        response.data?.token?.let { token ->
                              sharedPreference.edit()
                                    .putString(Constants.KEY_JWT_TOKEN, token)
                                    .apply()
                        }
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

      override suspend fun authenticate(): SimpleResource {
            return try {
                  val response = api.authenticate()
                  if (response.isSuccessful) {
                        Resource.Success(Unit)
                  } else {
                        Resource.Error(UiText.StringResource(R.string.error_unknown))
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