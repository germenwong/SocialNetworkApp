package com.hgm.socialnetworktwitch.feature_post.domain.use_case

/**
 * @auth：HGM
 * @date：2023-10-19 16:14
 * @desc：
 */
data class PostUseCases(
      val createPostUseCase: CreatePostUseCase,
      val getPostDetailUseCase: GetPostDetailUseCase,
      val getPostsForFollowsUseCase: GetPostsForFollowsUseCase,
      val getCommentForPostUseCase:GetCommentForPostUseCase
)
