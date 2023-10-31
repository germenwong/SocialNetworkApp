package com.hgm.socialnetworktwitch.core.domain.use_case

import android.content.SharedPreferences
import com.hgm.socialnetworktwitch.core.util.Constants

/**
 * @auth：HGM
 * @date：2023-10-25 10:42
 * @desc：
 */
class GetOwnUserIdUseCase(
      private val sharedPreferences: SharedPreferences
) {
      operator fun invoke(): String {
            return sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: ""
      }
}