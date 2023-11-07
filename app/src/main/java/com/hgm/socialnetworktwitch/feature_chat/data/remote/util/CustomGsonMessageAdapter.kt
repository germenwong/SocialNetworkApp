package com.hgm.socialnetworktwitch.feature_chat.data.remote.util

import com.google.gson.Gson
import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.WsClientMessage
import com.hgm.socialnetworktwitch.feature_chat.data.remote.dto.WsServerMessage
import com.tinder.scarlet.Message
import com.tinder.scarlet.MessageAdapter
import java.lang.reflect.Type

/**
 * @auth：HGM
 * @date：2023-11-03 15:50
 * @desc：
 */
class CustomGsonMessageAdapter<T> private constructor(
      private val gson: Gson
) : MessageAdapter<T> {
      //接收消息
      override fun fromMessage(message: Message): T {
            val payload = when (message) {
                  is Message.Text -> message.value
                  is Message.Bytes -> ""
            }

            val tagIndex = payload.indexOf("#")
            if (tagIndex == -1) {
                  return Any() as T
            }

            //截取类型和内容
            val type = payload.substring(0, tagIndex).toIntOrNull() ?: return Any() as T
            val json = payload.substring(tagIndex + 1, payload.length)
            val clazz = when (type) {
                  WebSocketObject.MESSAGE.ordinal -> WsServerMessage::class.java
                  else -> Any::class.java
            }
            return gson.fromJson(json, clazz) as T
      }

      //发送消息
      override fun toMessage(data: T): Message {
            val clazz = when (data) {
                  is WsClientMessage -> WsClientMessage::class.java
                  else -> Any::class.java
            }
            val type = when (data) {
                  is WsClientMessage -> WebSocketObject.MESSAGE.ordinal
                  else -> -1
            }
            val socketString = "$type#${gson.toJson(data, clazz)}"
            return Message.Text(socketString)
      }


      class Factory(
            private val gson: Gson = DEFAULT_GSON
      ) : MessageAdapter.Factory {
            override fun create(type: Type, annotations: Array<Annotation>): MessageAdapter<*> {
                  return CustomGsonMessageAdapter<Any>(gson)
            }
      }

      companion object {
            private val DEFAULT_GSON = Gson()
      }
}