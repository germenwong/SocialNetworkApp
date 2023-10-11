package com.hgm.socialnetworktwitch.presentation.post_detail

/**
 * @auth：HGM
 * @date：2023-10-10 21:06
 * @desc：
 */
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.Comment
import com.hgm.socialnetworktwitch.domain.model.Post
import com.hgm.socialnetworktwitch.presentation.main_feed.components.ActionRow
import com.hgm.socialnetworktwitch.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.presentation.post_detail.components.CommentView
import com.hgm.socialnetworktwitch.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.presentation.ui.theme.ProfilePictureSize
import com.hgm.socialnetworktwitch.presentation.ui.theme.ProfilePictureSmallSize
import com.hgm.socialnetworktwitch.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceSmall

@Composable
fun PostDetailScreen(
      navController: NavController,
      post: Post
) {
      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  navController = navController,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_feed),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  },
                  modifier = Modifier.fillMaxWidth(),
                  showBackIcon = true,
            )

            LazyColumn(
                  modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
            ) {
                  item {
                        Column(
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                        ) {
                              Spacer(modifier = Modifier.height(SpaceLarge))
                              Box(
                                    modifier = Modifier.fillMaxSize(),
                              ) {
                                    Column(
                                          modifier = Modifier
                                                .fillMaxSize()
                                                .offset(y = ProfilePictureSize / 2f)//内容偏移头像的一半
                                                .clip(RoundedCornerShape(RoundedCornerMedium))
                                                .shadow(5.dp)
                                                .background(MediumGray)
                                    ) {
                                          Image(
                                                painter = painterResource(id = R.drawable.kermit),
                                                contentDescription = "Post image",
                                                modifier = Modifier.fillMaxWidth()
                                          )
                                          Column(
                                                modifier = Modifier
                                                      .fillMaxWidth()
                                                      .padding(SpaceLarge)
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
                                                      text = post.description,
                                                      style = MaterialTheme.typography.bodyMedium,
                                                )
                                                Spacer(modifier = Modifier.height(SpaceMedium))
                                                Text(
                                                      text = stringResource(
                                                            id = R.string.liked_by_x_people,
                                                            post.likeCount
                                                      ),
                                                      style = MaterialTheme.typography.displayMedium,
                                                      fontSize = 16.sp
                                                )
                                          }
                                    }
                                    Image(
                                          painter = painterResource(id = R.drawable.germen),
                                          contentDescription = "Profile picture",
                                          modifier = Modifier
                                                .size(ProfilePictureSize)
                                                .clip(CircleShape)
                                                .align(Alignment.TopCenter)
                                    )
                              }
                        }
                        Spacer(modifier = Modifier.height(SpaceLarge*2))
                  }

                  items(5) {
                        CommentView(
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                          horizontal = SpaceLarge,
                                          vertical = SpaceSmall
                                    ),
                              comment = Comment(
                                    username = "Anthony",
                                    comment = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                                            "diam nonumy eirmod tempor invidunt ut labore et dolore " +
                                            "magna aliquyam erat...",
                                    likeCount = 354,
                              )
                        )
                  }
            }
      }
}
