package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component

/**
 * @auth：HGM
 * @date：2023-10-12 10:15
 * @desc：用户操作的信息行
 */
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ActionRow(
      username: String,
      isLike: Boolean = false,
      isOwnPost: Boolean = false,
      modifier: Modifier = Modifier,
      onLikeClick: () -> Unit = {},
      onShareClick: () -> Unit = {},
      onCommentClick: () -> Unit = {},
      onUsernameClick: () -> Unit = {},
      onDeleteClick: () -> Unit = {}
) {
      Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
      ) {
            Text(
                  text = username,
                  style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                  ),
                  modifier = Modifier.clickable { onUsernameClick() }
            )
            EngagementButton(
                  isLike = isLike,
                  isOwnPost = isOwnPost,
                  onLikeClick = onLikeClick,
                  onCommentClick = onCommentClick,
                  onShareClick = onShareClick,
                  onDeleteClick = onDeleteClick
            )
      }
}

