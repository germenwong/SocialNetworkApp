package com.hgm.socialnetworktwitch.feature_chat.presentation.chat

import com.hgm.socialnetworktwitch.feature_chat.domain.model.Chat

/**
 * @auth：HGM
 * @date：2023-11-04 20:16
 * @desc：
 */
data class ChatState(
      val isLoading: Boolean = false,
      val chats: List<Chat> = emptyList()
)
