package com.hgm.socialnetworktwitch.presentation.activity.components

/**
 * @auth：HGM
 * @date：2023-10-11 14:20
 * @desc：
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.Activity
import com.hgm.socialnetworktwitch.domain.util.ActivityAction
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium

@Composable
fun ActivityItem(
      activity: Activity,
      modifier: Modifier = Modifier
) {

      Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface)
      ) {
            Row(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(SpaceMedium),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
            ) {
                  // 判读用户的操作显示对应的文本
                  val fillerText = when (activity.actionType) {
                        ActivityAction.CommentOnPost -> stringResource(id = R.string.comment_on)
                        ActivityAction.LikedPost -> stringResource(id = R.string.liked)
                        ActivityAction.FollowedYou-> stringResource(id = R.string.followed_you)
                  }
                  val actionText = when (activity.actionType) {
                        ActivityAction.CommentOnPost -> stringResource(id = R.string.your_post)
                        ActivityAction.LikedPost -> stringResource(id = R.string.your_post)
                        ActivityAction.FollowedYou-> ""
                  }
                  Text(
                        text = buildAnnotatedString {
                              val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
                              withStyle(boldStyle) {
                                    append(activity.username)
                              }
                              append(" $fillerText ")
                              withStyle(boldStyle) {
                                    append(actionText)
                              }
                        },
                        style = MaterialTheme.typography.bodyLarge
                  )
                  Text(
                        text = activity.formattedTime,
                        textAlign = TextAlign.Right
                  )
            }
      }
}
