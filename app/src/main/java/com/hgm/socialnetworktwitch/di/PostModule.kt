package com.hgm.socialnetworktwitch.di

import com.google.gson.Gson
import com.hgm.socialnetworktwitch.feature_post.data.remote.PostApi
import com.hgm.socialnetworktwitch.core.util.Constants.BASE_URL
import com.hgm.socialnetworktwitch.feature_post.data.repository.PostRepositoryImpl
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.AddComment
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.CreatePost
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.DeletePost
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.GetCommentForPost
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.GetLikesForParent
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.GetPostDetail
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.GetPostsForFollows
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.hgm.socialnetworktwitch.feature_post.domain.use_case.UpdateLikeParent
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
object PostModule {

      @Provides
      @Singleton
      fun providePostApi(client: OkHttpClient): PostApi {
            return Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
                  .create(PostApi::class.java)
      }

      @Provides
      @Singleton
      fun providePostRepository(api: PostApi, gson: Gson): PostRepository {
            return PostRepositoryImpl(api, gson)
      }


      @Provides
      @Singleton
      fun providePostUseCase(repository: PostRepository): PostUseCases {
            return PostUseCases(
                  createPost = CreatePost(repository),
                  addComment = AddComment(repository),
                  getPostDetail = GetPostDetail(repository),
                  updateLikeParent = UpdateLikeParent(repository),
                  getLikesForParent = GetLikesForParent(repository),
                  getPostsForFollows = GetPostsForFollows(repository),
                  getCommentForPost = GetCommentForPost(repository),
                  deletePost = DeletePost(repository)
            )
      }
}