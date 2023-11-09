package com.hgm.socialnetworktwitch.feature_chat.presentation.message.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.TextWhite

/**
 * @auth：HGM
 * @date：2023-11-02 10:29
 * @desc：消息组件
 */
@Composable
fun RemoteMessage(
      message: String,
      context: Context,
      formattedTime: String,
      //triangleWidth: Dp = 20.dp,//三角形宽度
      //triangleHeight: Dp = 20.dp,//三角形高度
      modifier: Modifier = Modifier,
      textColor: Color = TextWhite,
      color: Color = MaterialTheme.colorScheme.onSurface,
      remoteUserProfilePictureUrl:String?
) {
      //val cornerRadius = MaterialTheme.shapes.medium.bottomStart


      Row(
            modifier = modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
            //.drawBehind {
            //      val cornerRadiusPx = cornerRadius.toPx(
            //            shapeSize = size,
            //            density = Density(density)
            //      )
            //      val path = Path().apply {
            //            moveTo(0f, size.height - cornerRadiusPx)
            //            lineTo(0f, size.height + triangleHeight.toPx())
            //            lineTo(triangleWidth.toPx(), size.height - cornerRadiusPx)
            //            close()
            //      }
            //      drawPath(
            //            path = path,
            //            color = color
            //      )
            //}
      ) {
            AsyncImage(
                  model = ImageRequest.Builder(context)
                        .data(remoteUserProfilePictureUrl)
                        .crossfade(true)
                        .build(),
                  contentDescription = stringResource(id = R.string.profile_image),
                  modifier = Modifier
                        .clip(CircleShape)
                        .size(ProfilePictureSizeSmall)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Box(
                  modifier = Modifier
                        .wrapContentWidth()
                        .background(
                              color = color,
                              shape = MaterialTheme.shapes.small
                        )
                        .padding(12.dp)
            ) {
                  Text(text = message, color = textColor)
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            Text(text = formattedTime, modifier = Modifier.align(Alignment.Bottom))
      }
}