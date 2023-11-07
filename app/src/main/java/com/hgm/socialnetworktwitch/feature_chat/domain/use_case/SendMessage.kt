package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository

/**
 * @auth：HGM
 * @date：2023-11-03 16:41
 * @desc：发送消息
 */
class SendMessage(
      private val chatRepository: ChatRepository
) {
      operator fun invoke(receiveId: String, text: String, chatId: String?) {
            if (text.isBlank()) {
                  return
            }
            chatRepository.sendMessage(receiveId = receiveId, text = text.trim(), chatId = chatId)
      }
}