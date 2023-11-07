package com.hgm.socialnetworktwitch.feature_chat.data.repository

import android.util.Log
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ChatApi
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ChatService
import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.WsClientMessage
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Chat
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.HttpException
import java.io.IOException

/**
 * @auth：HGM
 * @date：2023-11-04 19:53
 * @desc：
 */
class ChatRepositoryImpl(
      private val chatService: ChatService,
      private val chatApi: ChatApi
) : ChatRepository {
      override suspend fun getChatsForUser(): Resource<List<Chat>> {
            return try {
                  val chats = chatApi.getChatsForUser().mapNotNull { it.toChat() }
                  Resource.Success(data = chats)
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override suspend fun getMessagesForChat(
            chatId: String,
            page: Int,
            pageSize: Int
      ): Resource<List<Message>> {
            return try {
                  val messages = chatApi.getMessagesForChat(
                        chatId = chatId,
                        page = page,
                        pageSize = pageSize
                  ).map { it.toMessage() }
                  Resource.Success(messages)
            } catch (e: IOException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_couldnt_reach_srver)
                  )
            } catch (e: HttpException) {
                  Resource.Error(
                        uiText = UiText.StringResource(R.string.error_something_wrong)
                  )
            }
      }

      override fun sendMessage(receiveId: String, text: String, chatId: String?) {
            chatService.sendMessage(
                  WsClientMessage(
                        receiveId = receiveId,
                        chatId = chatId,
                        text = text
                  )
            )
      }

      override fun receiveMessage(): Flow<Message> {
            return chatService
                  .observeMessages()
                  .receiveAsFlow()
                  .onEach { message->
                        Log.d("Receiver", "接收到了来自服务器的消息：$message")
                  }
                  .map { it.toMessage() }
      }

      override fun observeChatEvents(): Flow<WebSocket.Event> {
            return chatService.observeEvents().receiveAsFlow()
      }
}