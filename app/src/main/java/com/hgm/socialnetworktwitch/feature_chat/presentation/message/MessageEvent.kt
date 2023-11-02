package com.hgm.socialnetworktwitch.feature_chat.presentation.message

/**
 * @auth：HGM
 * @date：2023-11-02 10:06
 * @desc：
 */
sealed class MessageEvent {
      data class EnterMessage(val message: String) : MessageEvent()
      object SendMessage : MessageEvent()
}
