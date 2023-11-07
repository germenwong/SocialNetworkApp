package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message

/**
 * @auth：HGM
 * @date：2023-11-06 23:08
 * @desc：
 */
data class MessageState(
      val messages:List<Message> = emptyList()
)
