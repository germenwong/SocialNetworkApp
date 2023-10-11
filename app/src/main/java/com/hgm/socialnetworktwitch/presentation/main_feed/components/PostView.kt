package com.hgm.socialnetworktwitch.presentation.main_feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.Post
import com.hgm.socialnetworktwitch.presentation.ui.theme.HintGray
import com.hgm.socialnetworktwitch.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.presentation.ui.theme.ProfilePictureMediumSize
import com.hgm.socialnetworktwitch.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.presentation.ui.theme.TextWhite
import com.hgm.socialnetworktwitch.util.Constants.POST_DESCRIPTION_MAX_LINE

/**
 * @auth：HGM
 * @date：2023-10-10 10:08
 * @desc：帖子组件
 */
@Composable
fun PostView(
      modifier: Modifier=Modifier,
      post: Post,
      showProfileImage: Boolean = true,
      onPostClick: () -> Unit = {}
) {
      Box(
            modifier = modifier
                  .fillMaxWidth()
                  .padding(SpaceMedium)
      ) {
            Column(
                  modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = if (showProfileImage) ProfilePictureMediumSize / 2f else 0.dp)//内容偏移头像的一半
                        .clip(RoundedCornerShape(RoundedCornerMedium))
                        .shadow(5.dp)
                        .background(MediumGray)
                        .clickable { onPostClick() }
            ) {
                  Image(
                        painter = painterResource(id = R.drawable.kermit),
                        contentDescription = "Post image"
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxWidth()
                              .padding(SpaceMedium)
                  ) {
                        ActionRow(
                              username = "Germen Wong",
                              modifier = Modifier.fillMaxWidth(),
                              onUsernameClick = { username ->

                              },
                              onLikeClick = { isLiked ->

                              },
                              onCommentClick = {

                              },
                              onShareClick = {

                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))
                        Text(
                              text = buildAnnotatedString {
                                    append(post.description)
                                    withStyle(
                                          SpanStyle(
                                                color = HintGray,
                                          )
                                    ) {
                                          append(
                                                LocalContext.current.getString(
                                                      R.string.read_more
                                                )
                                          )
                                    }
                              },
                              style = MaterialTheme.typography.bodyMedium,
                              overflow = TextOverflow.Ellipsis,
                              maxLines = POST_DESCRIPTION_MAX_LINE
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        Row(
                              modifier = Modifier.fillMaxWidth(),
                              horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                              Text(
                                    text = stringResource(
                                          id = R.string.liked_by_x_people,
                                          post.likeCount
                                    ),
                                    style = MaterialTheme.typography.displayMedium,
                                    fontSize = 16.sp
                              )
                              Text(
                                    text = stringResource(
                                          id = R.string.x_comments,
                                          post.commentCount
                                    ),
                                    style = MaterialTheme.typography.displayMedium,
                                    fontSize = 16.sp
                              )
                        }
                  }
            }
            if (showProfileImage) {
                  Image(
                        painter = painterResource(id = R.drawable.germen),
                        contentDescription = "Profile picture",
                        modifier = Modifier
                              .size(ProfilePictureMediumSize)
                              .clip(CircleShape)
                              .align(Alignment.TopCenter)
                  )
            }
      }
}


// 用户参与的三个按钮
@Composable
fun EngagementButton(
      modifier: Modifier = Modifier,
      isLike: Boolean = false,
      iconSize: Dp = 30.dp,
      onLikeClick: (Boolean) -> Unit = {},
      onCommentClick: () -> Unit = {},
      onShareClick: () -> Unit = {}
) {
      Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround
      ) {
            IconButton(
                  onClick = {
                        onLikeClick(!isLike)
                  },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Like button",
                        tint = if (isLike) {
                              Color.Red
                        } else {
                              TextWhite
                        }
                  )
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            IconButton(
                  onClick = {
                        onCommentClick()
                  },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Filled.Comment,
                        contentDescription = "Comment button"
                  )
            }
            Spacer(modifier = Modifier.width(SpaceMedium))
            IconButton(
                  onClick = {
                        onShareClick()
                  },
                  modifier = Modifier.size(iconSize)
            ) {
                  Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share button"
                  )
            }
      }
}


// 用户操作的信息行
@Composable
fun ActionRow(
      modifier: Modifier = Modifier,
      isLike: Boolean = false,
      username: String,
      onLikeClick: (Boolean) -> Unit = {},
      onCommentClick: () -> Unit = {},
      onShareClick: () -> Unit = {},
      onUsernameClick: (String) -> Unit = {}
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
                  modifier = Modifier.clickable { onUsernameClick(username) }
            )

            EngagementButton(
                  isLike = isLike,
                  onLikeClick = onLikeClick,
                  onCommentClick = onCommentClick,
                  onShareClick = onShareClick
            )
      }
}