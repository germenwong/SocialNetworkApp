package com.hgm.socialnetworktwitch.core.util

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.inputmethodservice.InputMethodService
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
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


/** 分享帖子l链接 */
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