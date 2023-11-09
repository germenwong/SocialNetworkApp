package com.hgm.socialnetworktwitch.core.util

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import com.hgm.socialnetworktwitch.R

/**
 * @auth：HGM
 * @date：2023-10-11 16:20
 * @desc：扩展
 */

/** Dp转Px */
fun Dp.toPx(): Float {
      return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.value,
            Resources.getSystem().displayMetrics
      )
}


/** 拉起键盘 */
fun Context.showKeyboard() {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.showSoftInput(null, InputMethodManager.SHOW_FORCED)
}


/** 分享帖子链接 */
fun Context.sharePostIntent(postId: String) {
      val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                  Intent.EXTRA_TEXT,
                  getString(
                        R.string.share_intent_text,
                        Constants.DEEP_LINK + "/$postId"
                  )
            )
      }

      if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, R.string.share_post.toString()))
      }
}

/** 点击输入框以外区域隐藏键盘 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autoHideKeyboard(): Modifier = composed {
      //LocalSoftwareKeyboardController 这个是compose 组件，必须在compose 函数内才能使用
      val keyboardController = LocalSoftwareKeyboardController.current
      pointerInput(this) {
            detectTapGestures(
                  onPress = {
                        keyboardController?.hide()
                  }
            )
      }
}