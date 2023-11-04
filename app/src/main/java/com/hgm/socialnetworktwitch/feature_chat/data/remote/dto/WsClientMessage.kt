package com.hgm.socialnetworktwitch.feature_chat.data.remote.dto


/**
 *  用于向服务器发起的消息
 **/
data class WsClientMessage(
    val receiveId: String,
    val text: String,
    val chatId: String?
)

