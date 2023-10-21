package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import android.view.SearchEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.User
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.feature_post.presentation.person_list.UserProfileItem


@ExperimentalCoilApi
@Composable
fun SearchScreen(
      //imageLoader: ImageLoader,
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: SearchViewModel = hiltViewModel()
) {
      val state = viewModel.searchState.value
      Box(
            modifier = Modifier.fillMaxSize()
      ) {
            Column(
                  modifier = Modifier.fillMaxSize()
            ) {
                  StandardTopBar(
                        onNavigateUp = onNavigateUp,
                        showBackIcon = true,
                        title = {
                              Text(
                                    text = stringResource(id = R.string.search_for_users),
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                              )
                        }
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxSize()
                              .padding(SpaceLarge)
                  ) {
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.searchFieldState.value.text,
                              hint = stringResource(id = R.string.search),
                              error = "",
                              leadingIcon = Icons.Default.Search,
                              onValueChange = {
                                    //viewModel.onEvent(SearchEvent.Query(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceLarge))
                        LazyColumn(
                              modifier = Modifier.fillMaxSize()
                        ) {
                              //items(state.userItems) { user ->
                              //      UserProfileItem(
                              //            user = user,
                              //            //imageLoader = imageLoader,
                              //            actionIcon = {
                              //                  IconButton(
                              //                        onClick = {
                              //                              //viewModel.onEvent(SearchEvent.ToggleFollowState(user.userId))
                              //                        },
                              //                        modifier = Modifier
                              //                              .size(IconSizeMedium)
                              //                  ) {
                              //                        Icon(
                              //                              imageVector = if (user.isFollowing) {
                              //                                    Icons.Default.PersonRemove
                              //                              } else Icons.Default.PersonAdd,
                              //                              contentDescription = null,
                              //                              tint = MaterialTheme.colorScheme.onBackground,
                              //                        )
                              //                  }
                              //            },
                              //            onItemClick = {
                              //                  onNavigate(
                              //                        Screen.ProfileScreen.route + "?userId=${user.userId}"
                              //                  )
                              //            }
                              //      )
                              //      Spacer(modifier = Modifier.height(SpaceMedium))
                              //}
                              item(2) {
                                    UserProfileItem(
                                          user = User(
                                                userId = "6528ee7761701d6eccb8c9ad",
                                                profilePictureUrl = "",
                                                username = "",
                                                description = "",
                                                followingCount = 0,
                                                followerCount = 0,
                                                postCount = 0
                                          ),
                                          onItemClick = {
                                                onNavigate(Screen.ProfileScreen.route+"?userId=6528ee7761701d6eccb8c9ad")
                                          }
                                    )
                              }
                        }
                  }
            }
            if (state.isLoading) {
                  CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                  )
            }
      }
}