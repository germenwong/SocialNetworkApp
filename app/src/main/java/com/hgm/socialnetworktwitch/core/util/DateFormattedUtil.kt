package com.hgm.socialnetworktwitch.core.util

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @auth：HGM
 * @date：2023-10-11 15:10
 * @desc：
 */
object DateFormattedUtil {

      // 时间戳格式化
      fun timestampToString(timestamp: Long, pattern: String): String {
            return SimpleDateFormat(pattern, Locale.getDefault()).run {
                  format(timestamp)
            }
      }
}