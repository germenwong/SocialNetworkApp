package com.hgm.socialnetworktwitch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @auth：HGM
 * @date：2023-09-22 15:52
 * @desc：
 */
@HiltAndroidApp
class MyApp:Application() {

      override fun onCreate() {
            super.onCreate()
            //if(BuildConfig.DEBUG) {
            //      Timber.plant(Timber.DebugTree())
            //}
      }
}