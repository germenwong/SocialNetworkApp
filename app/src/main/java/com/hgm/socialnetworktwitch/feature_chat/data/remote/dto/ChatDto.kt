package com.hgm.socialnetworktwitch.feature_chat.data.remote.dto

import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Chat

data class ChatDto(
    val chatId:String,
    val remoteUserId:String?,
    val remoteUsername: String?,
    val remoteProfilePictureUrl: String?,
    val lastMessage: String?,
    val timestamp: Long?
){
    fun toChat(): Chat?{
        return Chat(
            chatId = chatId,
            remoteUserId=remoteUserId ?: return null,
            remoteUsername=remoteUsername  ?: return null,
            remoteProfilePictureUrl = remoteProfilePictureUrl  ?: return null,
            lastMessage=lastMessage  ?: return null,
            formattedTime=DateFormattedUtil.timestampToString(timestamp  ?: return null,"MM.dd  HH:mm")
        )
    }
}