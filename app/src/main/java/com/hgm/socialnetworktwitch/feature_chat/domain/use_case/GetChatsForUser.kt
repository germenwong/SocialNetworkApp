package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Chat
import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-11-04 16:29
 * @desc：获取用户的所有聊天会话
 */
class GetChatsForUser(
      private val repository: ChatRepository
) {
      suspend operator fun invoke(): Resource<List<Chat>> {
            return repository.getChatsForUser()
      }

}