package com.hgm.socialnetworktwitch.feature_chat.presentation.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.TextWhite

/**
 * @auth：HGM
 * @date：2023-11-02 10:29
 * @desc：消息组件
 */
@Composable
fun RemoteMessage(
      message: String,
      formattedTime: String,
      triangleWidth: Dp = 20.dp,//三角形宽度
      triangleHeight: Dp = 20.dp,//三角形高度
      modifier: Modifier = Modifier,
      textColor: Color = TextWhite,
      color: Color = MaterialTheme.colorScheme.onSurface
) {
      val cornerRadius = MaterialTheme.shapes.medium.bottomStart


      Row(
            modifier = modifier
                  .fillMaxWidth()
                  .drawBehind {
                        val cornerRadiusPx = cornerRadius.toPx(
                              shapeSize = size,
                              density = Density(density)
                        )
                        val path = Path().apply {
                              moveTo(0f, size.height - cornerRadiusPx)
                              lineTo(0f, size.height + triangleHeight.toPx())
                              lineTo(triangleWidth.toPx(), size.height - cornerRadiusPx)
                              close()
                        }
                        drawPath(
                              path = path,
                              color = color
                        )
                  }
      ) {
            Box(
                  modifier = Modifier
                        .weight(1f)
                        .background(
                              color = MaterialTheme.colorScheme.surface,
                              shape = MaterialTheme.shapes.small
                        )
                        .padding(SpaceMedium)
            ) {
                  Text(text = message, color = textColor)
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            Text(text = formattedTime, modifier = Modifier.align(Alignment.Bottom))
      }
}