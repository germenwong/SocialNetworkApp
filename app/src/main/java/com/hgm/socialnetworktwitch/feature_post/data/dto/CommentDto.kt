package com.hgm.socialnetworktwitch.feature_post.data.dto

import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment


data class CommentDto(
      val id: String,
      val profilePictureUrl: String,
      val username: String,
      val timestamp: Long,
      val comment: String,
      val likeCount: Int,
      val isLiked: Boolean
) {
      fun toComment(): Comment {
            return Comment(
                  comment = comment,
                  username = username,
                  likeCount = likeCount,
                  id = id,
                  profilePictureUrl = profilePictureUrl,
                  isLiked = isLiked,
                  formattedTime = DateFormattedUtil.timestampToString(
                        timestamp = timestamp,
                        pattern = "yyyy-MM-dd HH:mm"
                  ),
            )
      }
}
