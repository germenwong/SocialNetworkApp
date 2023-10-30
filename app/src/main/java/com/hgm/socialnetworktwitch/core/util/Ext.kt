package com.hgm.socialnetworktwitch.core.util

import android.content.Context
import android.content.res.Resources
import android.inputmethodservice.InputMethodService
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.unit.Dp

/**
 * @auth：HGM
 * @date：2023-10-11 16:20
 * @desc：扩展
 */
fun Dp.toPx(): Float {
      return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.value,
            Resources.getSystem().displayMetrics
      )
}


fun Context.showKeyboard() {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.showSoftInput(null, InputMethodManager.SHOW_FORCED)
}