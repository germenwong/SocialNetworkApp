package com.hgm.socialnetworktwitch.feature_chat.di

import com.google.gson.Gson
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ChatService
import com.hgm.socialnetworktwitch.feature_chat.data.remote.util.CustomGsonMessageAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient

/**
 * @auth：HGM
 * @date：2023-11-07 16:55
 * @desc：
 */
object ScarletInstance {

      var current: ChatService? = null

      fun getNewInstance(client: OkHttpClient)  : ChatService{
            return Scarlet.Builder()
                  .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(Gson()))
                  .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
                  .webSocketFactory(
                        //client.newWebSocketFactory("ws://172.20.10.4:8080/api/chat/websocket")
                        client.newWebSocketFactory("ws://192.168.31.169:8080/api/chat/websocket")
                  )
                  .build()
                  .create(ChatService::class.java)
                  .also {
                        current = it
                  }
      }
}