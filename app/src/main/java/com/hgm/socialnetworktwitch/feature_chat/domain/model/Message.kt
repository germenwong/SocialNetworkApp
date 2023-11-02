package com.hgm.socialnetworktwitch.feature_chat.domain.model

/**
 * @auth：HGM
 * @date：2023-11-02 10:10
 * @desc：
 */
data class Message(
      val sendId: String,
      val receiveId: String,
      val text: String,
      val formattedTime: String,
      val chatId:String,
      val id: String
)
