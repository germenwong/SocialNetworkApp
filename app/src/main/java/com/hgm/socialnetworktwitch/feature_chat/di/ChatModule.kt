package com.hgm.socialnetworktwitch.feature_chat.di

import android.app.Application
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ws.util.CustomGsonMessageAdapter
import com.hgm.socialnetworktwitch.feature_chat.data.remote.ws.util.FlowStreamAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
      fun provideScarlet(app: Application, client: OkHttpClient): Scarlet {
            return Scarlet.Builder()
                  .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory())
                  .addStreamAdapterFactory(FlowStreamAdapter.Factory)
                  .webSocketFactory(
                        client.newWebSocketFactory("ws://192.168.31.161:8080")
                  )
                  .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
                  .build()
      }



}