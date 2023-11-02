package com.hgm.socialnetworktwitch.feature_chat.domain.model

/**
 * @auth：HGM
 * @date：2023-11-01 15:37
 * @desc：
 */
data class ChatItem(
      val username: String,
      val lastMessage: String,
      val profilePictureUrl: String,
      val lastMessageTime: String
)
