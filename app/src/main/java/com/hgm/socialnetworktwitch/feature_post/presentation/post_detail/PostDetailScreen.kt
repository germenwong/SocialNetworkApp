package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component.ActionRow
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: PostDetailViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val state = viewModel.state.value

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.Navigate -> onNavigate(event.route)
                        is UiEvent.NavigateUp -> onNavigateUp()
                        is UiEvent.ShowSnackBar -> {
                              snackBarState.showSnackbar(event.uiText.asString(context))
                        }
                  }
            }
      }


      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  onNavigateUp = onNavigateUp,
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
                              state.post?.let { post ->
                                    Spacer(modifier = Modifier.height(SpaceLarge))
                                    Box(
                                          modifier = Modifier.fillMaxSize(),
                                    ) {
                                          Column(
                                                modifier = Modifier
                                                      .fillMaxSize()
                                                      .offset(y = ProfilePictureSizeMedium / 2f)//内容偏移头像的一半
                                                      .clip(RoundedCornerShape(RoundedCornerMedium))
                                                      .shadow(5.dp)
                                                      .background(MediumGray)
                                          ) {
                                                AsyncImage(
                                                      model = ImageRequest.Builder(context)
                                                            .data(post.imageUrl)
                                                            .crossfade(true)
                                                            .build(),
                                                      contentDescription = stringResource(id = R.string.post_image),
                                                      modifier = Modifier.fillMaxWidth()
                                                )
                                                Column(
                                                      modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(SpaceLarge)
                                                ) {
                                                      ActionRow(
                                                            username = post.username,
                                                            modifier = Modifier.fillMaxWidth(),
                                                            onUsernameClick = { username ->
                                                                  //onNavigate(Screen.ProfileScreen.route + "?userId=${activity.userId}")
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
                                          AsyncImage(
                                                model = ImageRequest.Builder(context)
                                                      .data(post.profilePictureUrl)
                                                      .crossfade(true)
                                                      .build(),
                                                contentDescription = stringResource(id = R.string.profile_image),
                                                modifier = Modifier
                                                      .size(ProfilePictureSizeMedium)
                                                      .clip(CircleShape)
                                                      .align(Alignment.TopCenter)
                                          )
                                          if (state.isLoadingPost) {
                                                CircularProgressIndicator(
                                                      modifier = Modifier.align(
                                                            Alignment.Center
                                                      )
                                                )
                                          }
                                    }
                              }
                        }
                        //Divider()
                        Spacer(modifier = Modifier.height(SpaceLarge * 2))
                  }

                  items(state.comments) { comment ->
                        CommentView(
                              context = context,
                              comment = comment,
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                          horizontal = SpaceLarge,
                                          vertical = SpaceSmall
                                    )
                        )
                  }
            }
      }
}
