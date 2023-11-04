package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-11-04 16:29
 * @desc：观察聊天事件
 */
class ObserveChatEvents(
      private val repository: ChatRepository
) {
      operator fun invoke(): Flow<WebSocket.Event> {
            return repository.observeChatEvents()
      }

}