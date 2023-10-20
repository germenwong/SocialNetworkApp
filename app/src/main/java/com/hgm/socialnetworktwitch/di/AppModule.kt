package com.hgm.socialnetworktwitch.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hgm.socialnetworktwitch.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

      @Provides
      @Singleton
      fun provideJwToken(sharedPreferences: SharedPreferences): String {
            return sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "") ?: ""
      }

      @Provides
      @Singleton
      fun provideOKHttpClient(token: String): OkHttpClient {
            return OkHttpClient.Builder()
                  .addInterceptor {
                        val newRequest = it.request().newBuilder()
                              .addHeader("Authorization", "Bearer $token")
                              .build()
                        it.proceed(newRequest)
                  }
                  .build()
      }

      @Provides
      @Singleton
      fun provideSharedPreference(app: Application): SharedPreferences {
            return app.getSharedPreferences(
                  Constants.SHARED_PREF_NAME,
                  MODE_PRIVATE
            )
      }

      @Provides
      @Singleton
      fun provideGson(): Gson {
            return Gson()
      }

}