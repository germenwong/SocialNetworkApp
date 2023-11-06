package com.hgm.socialnetworktwitch.feature_chat.data.remote.dto

import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message

data class MessageDto(
      val sendId: String,
      val receiveId: String,
      val text: String,
      val timestamp: Long,
      val chatId: String?,
      val id: String
) {
      fun toMessage(): Message {
            return Message(
                  sendId = sendId,
                  receiveId = receiveId,
                  text = text,
                  formattedTime = DateFormattedUtil.timestampToString(timestamp, "HH:mm"),
                  chatId = chatId,
            )
      }
}
