package com.hgm.socialnetworktwitch.feature_post.domain.use_case

/**
 * @auth：HGM
 * @date：2023-10-19 16:14
 * @desc：
 */
data class PostUseCases(
      val deletePost: DeletePost,
      val createPost: CreatePost,
      val addComment: AddComment,
      val getPostDetail: GetPostDetail,
      val updateLikeParent: UpdateLikeParent,
      val getLikesForParent: GetLikesForParent,
      val getPostsForFollows: GetPostsForFollows,
      val getCommentForPost:GetCommentForPost
)
