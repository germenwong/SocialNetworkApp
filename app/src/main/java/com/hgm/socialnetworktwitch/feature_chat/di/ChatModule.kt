package com.hgm.socialnetworktwitch.feature_chat.di

import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ChatApi
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ChatService
import com.hgm.socialnetworktwitch.feature_chat.data.remote.util.CustomGsonMessageAdapter
import com.hgm.socialnetworktwitch.feature_chat.data.remote.util.FlowStreamAdapter
import com.hgm.socialnetworktwitch.feature_chat.data.repository.ChatRepositoryImpl
import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.GetChatsForUser
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.GetMessagesForChat
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ObserveChatEvents
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.ReceiveMessage
import com.hgm.socialnetworktwitch.feature_chat.domain.use_case.SendMessage
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @auth：HGM
 * @date：2023-11-03 15:44
 * @desc：
 */
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

      @Provides
      @Singleton
      fun provideChatApi(client: OkHttpClient): ChatApi {
            return Retrofit.Builder()
                  .baseUrl(Constants.BASE_URL)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
                  .create(ChatApi::class.java)
      }

      @Provides
      @Singleton
      fun provideScarlet(/*app: Application,*/ client: OkHttpClient): Scarlet {
            return Scarlet.Builder()
                  .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory())
                  .addStreamAdapterFactory(FlowStreamAdapter.Factory)
                  .webSocketFactory(
                        client.newWebSocketFactory("ws://172.20.10.4:8080/api/chat/websocket")
                  )
                  //.lifecycle(AndroidLifecycle.ofApplicationForeground(app))
                  .build()
      }


      @Provides
      @Singleton
      fun provideChatService(scarlet: Scarlet): ChatService {
            return scarlet.create(ChatService::class.java)
      }

      @Provides
      @Singleton
      fun provideChatUseCases(repository: ChatRepository): ChatUseCases {
            return ChatUseCases(
                  sendMessage = SendMessage(repository),
                  receiveMessage = ReceiveMessage(repository),
                  getChatsForUser = GetChatsForUser(repository),
                  observeChatEvents = ObserveChatEvents(repository),
                  getMessagesForChat = GetMessagesForChat(repository)
            )
      }


      @Provides
      @Singleton
      fun provideChatRepository(chatService: ChatService, chatApi: ChatApi): ChatRepository {
            return ChatRepositoryImpl(chatService, chatApi)
      }

}