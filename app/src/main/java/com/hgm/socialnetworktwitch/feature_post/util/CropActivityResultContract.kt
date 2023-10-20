package com.hgm.socialnetworktwitch.feature_post.util

import android.content.Context
import android.content.Intent
import android.content.RestrictionsManager.RESULT_ERROR
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.hgm.socialnetworktwitch.core.domain.util.getFileName
import com.yalantis.ucrop.UCrop
import java.io.File

/**
 * 自定义Activity的回调，用于获取裁剪图片的Uri
 */
class CropActivityResultContract(
      private val aspectRatioX: Float,
      private val aspectRatioY: Float,
) : ActivityResultContract<Uri, Uri?>() {

      override fun createIntent(context: Context, input: Uri): Intent {
            return UCrop.of(
                  input,//需要裁剪的照片
                  Uri.fromFile(//在app缓存目录新建以裁剪照片名字的文件，转成Uri格式接收裁剪后的Uri
                        File(
                              context.cacheDir,
                              context.contentResolver.getFileName(input)
                        )
                  )
            )
                  .withAspectRatio(aspectRatioX, aspectRatioY)
                  .getIntent(context)
      }

      override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            if (intent == null) {
                  return null
            }
            if (resultCode == RESULT_ERROR) {
                  val error = UCrop.getError(intent)
                  error?.printStackTrace()
            }
            return UCrop.getOutput(intent)
      }
}