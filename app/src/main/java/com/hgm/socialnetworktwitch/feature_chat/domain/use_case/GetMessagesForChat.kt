package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository

/**
 * @auth：HGM
 * @date：2023-11-04 21:59
 * @desc：获取某对话的所有信息
 */
class GetMessagesForChat(
      private val repository: ChatRepository
) {
      suspend operator fun invoke(
            chatId: String,
            page: Int,
            pageSize: Int=Constants.PAGE_DEFAULT_SIZE
      ): Resource<List<Message>> {
            return repository.getMessagesForChat(chatId, page, pageSize)
      }
}