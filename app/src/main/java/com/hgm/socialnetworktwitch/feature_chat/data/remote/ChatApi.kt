package com.hgm.socialnetworktwitch.feature_chat.data.remote

import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.ChatDto
import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.MessageDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @auth：HGM
 * @date：2023-11-04 17:07
 * @desc：
 */
interface ChatApi {

      @GET("/api/chat/chats")
      suspend fun getChatsForUser(): List<ChatDto>

      @GET("/api/chat/messages")
      suspend fun getMessagesForChat(
            @Query("chatId") chatId:String,
            @Query("page") page:Int,
            @Query("pageSize") pageSize:Int,
      ): List<MessageDto>
}