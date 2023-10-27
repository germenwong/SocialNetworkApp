package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

sealed class PostDetailEvent {
      object LikePost : PostDetailEvent()
      object LikeComment : PostDetailEvent()
      data class Comment(val comment:String) : PostDetailEvent()
      object SharedPost: PostDetailEvent()
}
