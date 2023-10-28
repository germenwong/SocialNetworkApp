package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

sealed class PostDetailEvent {
      object LikePost : PostDetailEvent()
      object LikeComment : PostDetailEvent()
      object Comment : PostDetailEvent()
      object SharedPost : PostDetailEvent()
      data class EnteredComment(val comment: String) : PostDetailEvent()
}
