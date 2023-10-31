package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

sealed class PostDetailEvent {
      object LikePost : PostDetailEvent()
      data class LikeComment(val commentId:String) : PostDetailEvent()
      object Comment : PostDetailEvent()
      data class EnteredComment(val comment: String) : PostDetailEvent()
}
