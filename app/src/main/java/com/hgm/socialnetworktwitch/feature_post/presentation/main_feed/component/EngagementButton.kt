package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component

/**
 * @auth：HGM
 * @date：2023-10-12 10:15
 * @desc：用户参与的三个按钮
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.TextWhite

@Composable
fun EngagementButton(
      isOwnPost: Boolean,
      iconSize: Dp = 30.dp,
      isLike: Boolean = false,
      modifier: Modifier = Modifier,
      onLikeClick: () -> Unit = {},
      onShareClick: () -> Unit = {},
      onDeleteClick: () -> Unit = {},
      onCommentClick: () -> Unit = {}
) {
      Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround
      ) {
            IconButton(
                  onClick = { onLikeClick() },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = stringResource(id = R.string.like_button),
                        tint = if (isLike) {
                              MaterialTheme.colorScheme.primary
                        } else TextWhite
                  )
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            IconButton(
                  onClick = { onCommentClick() },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Filled.Comment,
                        contentDescription = stringResource(id = R.string.comment_button),
                  )
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            IconButton(
                  onClick = { onShareClick() },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = stringResource(id = R.string.share_button)
                  )
            }
            if (isOwnPost) {
                  Spacer(modifier = Modifier.width(SpaceMedium))
                  IconButton(
                        onClick = { onDeleteClick() },
                        modifier = Modifier.size(iconSize)
                  ) {
                        Icon(
                              imageVector = Icons.Filled.Delete,
                              contentDescription = stringResource(id = R.string.delete_button)
                        )
                  }
            }
      }
}
