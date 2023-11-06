package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

/**
 * @auth：HGM
 * @date：2023-11-04 16:33
 * @desc：关于聊天的所有用例
 */
data class ChatUseCases(
      val sendMessage: SendMessage,
      val receiveMessage: ReceiveMessage,
      val getChatsForUser: GetChatsForUser,
      val observeChatEvents: ObserveChatEvents,
      val getMessagesForChat: GetMessagesForChat
)
