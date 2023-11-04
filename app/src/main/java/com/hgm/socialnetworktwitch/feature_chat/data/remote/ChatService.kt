package com.hgm.socialnetworktwitch.feature_chat.data.remote

import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.WsClientMessage
import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.WsServerMessage
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-11-03 16:34
 * @desc：
 */
interface ChatService {

      //用于观察App的声明周期自动帮我们管理WebSocket
      @Receive
      fun observeEvents(): Flow<WebSocket.Event>

      //向服务器发送消息
      @Send
      fun sendMessage(message: WsClientMessage)

      //接收服务器的消息
      @Receive
      fun observeMessages(): Flow<WsServerMessage>
}