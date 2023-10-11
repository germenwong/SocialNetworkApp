package com.hgm.socialnetworktwitch.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.Post
import com.hgm.socialnetworktwitch.domain.model.User
import com.hgm.socialnetworktwitch.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.presentation.main_feed.components.PostView
import com.hgm.socialnetworktwitch.presentation.profile.components.BannerSection
import com.hgm.socialnetworktwitch.presentation.profile.components.ProfileHeaderSection
import com.hgm.socialnetworktwitch.presentation.ui.theme.ProfilePictureLargeSize
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.presentation.util.Screen

/**
 * @auth：HGM
 * @date：2023-10-10 16:14
 * @desc：
 */
@Composable
fun ProfileScreen(
      navController: NavController
) {
      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  navController = navController,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_profile),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  },
                  modifier = Modifier.fillMaxWidth(),
            )

            LazyColumn(
                  modifier = Modifier.fillMaxSize(),
            ) {
                  item {
                        BannerSection(
                              modifier = Modifier.aspectRatio(2.5f)
                        )
                  }
                  item {
                        ProfileHeaderSection(
                              user = User(
                                    profilePictureUrl = "",
                                    username = "Germen Wong",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                                            "diam nonumy eirmod tempor invidunt ut labore et dolore " +
                                            "magna aliquyam erat...",
                                    followerCount = 123,
                                    followingCount = 12,
                                    postCount = 12
                              )
                        ) {
                              navController.navigate(Screen.EditProfileScreen.route)
                        }
                  }
                  items(5) {
                        Spacer(
                              modifier = Modifier
                                    .height(SpaceMedium)
                                    .offset(y = -ProfilePictureLargeSize / 2f)
                        )
                        PostView(
                              post = Post(
                                    username = "Germen Wong",
                                    imageUrl = "",
                                    profilePicture = "",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                                            "diam nonumy eirmod tempor invidunt ut labore et dolore " +
                                            "magna aliquyam erat...",
                                    likeCount = 14,
                                    commentCount = 53,
                              ),
                              showProfileImage = false,
                              // TODO：设置偏移后滑动会出现闪烁
                              modifier = Modifier.offset(y = -ProfilePictureLargeSize / 2f)
                        ) {
                              navController.navigate(Screen.PostDetailScreen.route)
                        }
                  }
            }
      }
}