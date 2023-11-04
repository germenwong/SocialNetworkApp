package com.hgm.socialnetworktwitch.feature_chat.data.remote

import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.ChatDto
import retrofit2.http.GET

/**
 * @auth：HGM
 * @date：2023-11-04 17:07
 * @desc：
 */
interface ChatApi {

      @GET("/api/chat/chats")
      suspend fun getChatsForUser(): List<ChatDto>
}