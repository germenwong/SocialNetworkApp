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
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.LightGreen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium

/**
 * @auth：HGM
 * @date：2023-11-02 10:29
 * @desc：消息组件
 */
@Composable
fun OwnMessage(
      message: String,
      formattedTime: String,
      color: Color = LightGreen,
      triangleWidth: Dp = 20.dp,//三角形宽度
      triangleHeight: Dp = 20.dp,//三角形高度
      modifier: Modifier = Modifier,
      textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
      val cornerRadius = MaterialTheme.shapes.medium.bottomEnd

      Row(
            modifier = modifier
                  .fillMaxWidth()
                  .drawBehind {
                        val cornerRadiusPx = cornerRadius.toPx(
                              shapeSize = size,
                              density = Density(density)
                        )
                        val path = Path().apply {
                              moveTo(size.width, size.height - cornerRadiusPx)
                              lineTo(size.width, size.height + triangleHeight.toPx())
                              lineTo(
                                    size.width - triangleWidth.toPx(),
                                    size.height - cornerRadiusPx
                              )
                              close()
                        }
                        drawPath(
                              path = path,
                              color = color
                        )
                  }
      ) {
            Text(text = formattedTime, modifier = Modifier.align(Alignment.Bottom))
            Spacer(modifier = Modifier.width(SpaceMedium))
            Box(
                  modifier = Modifier
                        .weight(1f)
                        .background(
                              color = color,
                              shape = MaterialTheme.shapes.small
                        )
                        .padding(SpaceMedium)
            ) {
                  Text(text = message, color = textColor)
            }
      }
}