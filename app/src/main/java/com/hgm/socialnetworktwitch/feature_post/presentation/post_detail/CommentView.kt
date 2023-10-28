package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail

/**
 * @auth：HGM
 * @date：2023-10-11 15:27
 * @desc：评论区组件
 */
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.TextWhite
import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil

@Composable
fun CommentView(
      context: Context,
      comment: Comment,
      modifier: Modifier = Modifier,
      onLikeClick: () -> Unit = {}
) {
      Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface)
      ) {
            Column(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(SpaceMedium)
            ) {
                  Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                  ) {
                        Row(
                              verticalAlignment = Alignment.CenterVertically
                        ) {
                              AsyncImage(
                                    model = ImageRequest.Builder(context)
                                          .data(comment.profilePictureUrl)
                                          .crossfade(true)
                                          .build(),
                                    contentDescription = stringResource(id = R.string.profile_image),
                                    modifier = Modifier
                                          .clip(CircleShape)
                                          .size(ProfilePictureSizeSmall)
                              )
                              Spacer(modifier = Modifier.width(SpaceSmall))
                              Column {
                                    Text(
                                          text = comment.username,
                                          fontWeight = FontWeight.Bold,
                                          style = MaterialTheme.typography.bodyMedium,
                                          color = MaterialTheme.colorScheme.onBackground
                                    )
                                    Text(
                                          text = comment.formattedTime,
                                          style = MaterialTheme.typography.bodyMedium
                                    )
                              }
                        }

                        IconButton(
                              modifier = Modifier.align(Alignment.Top),
                              onClick = { onLikeClick() }
                        ) {
                              Icon(
                                    imageVector = if (comment.isLiked) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                                    tint = if (comment.isLiked) MaterialTheme.colorScheme.primary else TextWhite,
                                    contentDescription = stringResource(id = R.string.like)
                              )
                        }
                  }

                  Spacer(modifier = Modifier.height(SpaceMedium))

                  Column(
                        modifier = Modifier
                              .fillMaxWidth(),
                  ) {
                        Text(
                              text = comment.comment,
                              style = MaterialTheme.typography.bodyMedium,
                              color = MaterialTheme.colorScheme.onBackground,
                              modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        Text(
                              text = stringResource(
                                    id = R.string.liked_by_x_people,
                                    comment.likeCount
                              ),
                              fontWeight = FontWeight.Bold,
                              style = MaterialTheme.typography.bodyMedium,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            }
      }
}


