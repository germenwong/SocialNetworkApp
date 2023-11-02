package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message

/**
 * @auth：HGM
 * @date：2023-11-02 10:06
 * @desc：
 */
data class MessageState(
      val isLoading: Boolean = false,
      val messages: List<Message> = emptyList()
)
