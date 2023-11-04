package com.hgm.socialnetworktwitch.feature_chat.domain.model

/**
 * @auth：HGM
 * @date：2023-11-01 15:37
 * @desc：
 */
data class Chat(
      val chatId:String,
      val remoteUserId:String,
      val remoteUsername: String,
      val remoteProfilePictureUrl: String,
      val lastMessage: String,
      val formattedTime: String
)
