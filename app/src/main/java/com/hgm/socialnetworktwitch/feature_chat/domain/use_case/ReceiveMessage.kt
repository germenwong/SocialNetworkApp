package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-11-04 16:29
 * @desc：接收聊天事件
 */
class ReceiveMessage(
      private val chatRepository: ChatRepository
) {
      operator fun invoke(): Flow<Message> {
            return chatRepository.receiveMessage()
      }

}