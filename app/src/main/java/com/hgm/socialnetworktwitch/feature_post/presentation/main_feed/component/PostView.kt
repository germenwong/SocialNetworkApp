package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.HintGray
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.util.Constants.POST_DESCRIPTION_MAX_LINE

/**
 * @auth：HGM
 * @date：2023-10-10 10:08
 * @desc：帖子组件
 */
@Composable
fun PostView(
      post: Post,
      onPostClick: () -> Unit = {},
      modifier: Modifier = Modifier,
      showProfileImage: Boolean = true
) {
      Box(
            modifier = modifier
                  .fillMaxWidth()
                  .padding(SpaceMedium)
      ) {
            Column(modifier = Modifier
                  .fillMaxWidth()
                  .offset(y = if (showProfileImage) ProfilePictureSizeMedium / 2f else 0.dp)//内容偏移头像的一半
                  .clip(RoundedCornerShape(RoundedCornerMedium))
                  .shadow(5.dp)
                  .background(MediumGray)
                  .clickable { onPostClick() }) {
                  AsyncImage(
                        model = post.imageUrl,
                        contentDescription = stringResource(id = R.string.post_image),
                        modifier = Modifier
                              .fillMaxWidth()
                              .height(200.dp),
                        contentScale = ContentScale.Crop
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxWidth()
                              .padding(SpaceMedium)
                  ) {
                        ActionRow(username = "Germen Wong",
                              modifier = Modifier.fillMaxWidth(),
                              onUsernameClick = { username ->

                              },
                              onLikeClick = { isLiked ->

                              },
                              onCommentClick = {

                              },
                              onShareClick = {

                              })
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
                                          id = R.string.liked_by_x_people, post.likeCount
                                    ),
                                    style = MaterialTheme.typography.displayMedium,
                                    fontSize = 16.sp
                              )
                              Text(
                                    text = stringResource(
                                          id = R.string.x_comments, post.commentCount
                                    ),
                                    style = MaterialTheme.typography.displayMedium,
                                    fontSize = 16.sp
                              )
                        }
                  }
            }
            if (showProfileImage) {
                  AsyncImage(
                        model = post.profilePictureUrl,
                        contentDescription = stringResource(id = R.string.profile_image),
                        modifier = Modifier
                              .size(ProfilePictureSizeMedium)
                              .clip(CircleShape)
                              .align(Alignment.TopCenter)
                  )
            }
      }
}

