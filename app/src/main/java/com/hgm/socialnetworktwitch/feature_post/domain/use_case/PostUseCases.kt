package com.hgm.socialnetworktwitch.feature_post.domain.use_case

/**
 * @auth：HGM
 * @date：2023-10-19 16:14
 * @desc：
 */
data class PostUseCases(
      val createPostUseCase: CreatePostUseCase,
      val addCommentUseCase: AddCommentUseCase,
      val getPostDetailUseCase: GetPostDetailUseCase,
      val updateLikeParentUseCase: UpdateLikeParentUseCase,
      val getLikesForParentUseCase: GetLikesForParentUseCase,
      val getPostsForFollowsUseCase: GetPostsForFollowsUseCase,
      val getCommentForPostUseCase:GetCommentForPostUseCase
)
