package com.hgm.socialnetworktwitch.di

import com.hgm.socialnetworktwitch.feature_post.data.remote.PostApi
import com.hgm.socialnetworktwitch.feature_profile.data.remote.ProfileApi
import com.hgm.socialnetworktwitch.feature_profile.data.repository.ProfileRepositoryImpl
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository
import com.hgm.socialnetworktwitch.feature_profile.domain.use_case.GetProfileUseCase
import com.hgm.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

      @Provides
      @Singleton
      fun provideProfileApi(client: OkHttpClient): ProfileApi {
            return Retrofit.Builder()
                  .baseUrl(ProfileApi.BASE_URL)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
                  .create(ProfileApi::class.java)
      }

      @Provides
      @Singleton
      fun provideProfileRepository(api: ProfileApi): ProfileRepository {
            return ProfileRepositoryImpl(api)
      }

      @Provides
      @Singleton
      fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
            return ProfileUseCases(
                  getProfileUseCase = GetProfileUseCase(repository)
            )
      }
}