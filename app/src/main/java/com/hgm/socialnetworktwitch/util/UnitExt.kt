package com.hgm.socialnetworktwitch.util

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp

/**
 * @auth：HGM
 * @date：2023-10-11 16:20
 * @desc：
 */

fun Dp.toPx(): Float {
      return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.value,
            Resources.getSystem().displayMetrics
      )
}