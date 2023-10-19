package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

/**
 * @auth：HGM
 * @date：2023-10-12 16:39
 * @desc：
 */
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.feature_auth.domain.model.User
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium

@Composable
fun PersonListScreen(
      navController: NavController
) {
      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  navController = navController,
                  showBackIcon = true,
                  title = {
                        Text(
                              text = stringResource(id = R.string.liked_by),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            )
            LazyColumn(
                  modifier = Modifier.fillMaxSize(),
                  contentPadding = PaddingValues(SpaceLarge)
            ) {
                  items(10) {
                        UserProfileItem(
                              user = User(
                                    profilePictureUrl = "",
                                    username = "Philipp Lackner",
                                    description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                            "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                            "magna aliquyam erat, sed diam voluptua",
                                    followerCount = 234,
                                    followingCount = 534,
                                    postCount = 65
                              ),
                              actionIcon = {
                                    Icon(
                                          imageVector = Icons.Default.PersonAdd,
                                          contentDescription = null,
                                          tint = MaterialTheme.colorScheme.onBackground,
                                          modifier = Modifier.size(IconSizeMedium)
                                    )
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                  }
            }
      }
}