package com.hgm.socialnetworktwitch.feature_post.presentation.post_detail


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.SendTextField
import com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component.ActionRow
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.showKeyboard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
      showKeyboard: Boolean,
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: PostDetailViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val state = viewModel.state.value
      val focusRequester = remember {
            FocusRequester()
      }

      LaunchedEffect(key1 = true) {
            if (showKeyboard) {
                  context.showKeyboard()
                  focusRequester.requestFocus()
            }

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
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.surface)
            ) {
                  item {
                        Column(
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                        ) {
                              state.post?.let { post ->
                                    Box(
                                          modifier = Modifier.fillMaxSize(),
                                    ) {
                                          Column(
                                                modifier = Modifier
                                                      .fillMaxSize()
                                                      .shadow(5.dp)
                                                      .background(MediumGray)
                                          ) {
                                                AsyncImage(
                                                      model = ImageRequest.Builder(context)
                                                            .data(post.imageUrl)
                                                            .crossfade(true)
                                                            .build(),
                                                      contentScale = ContentScale.Crop,
                                                      modifier = Modifier.fillMaxWidth(),
                                                      contentDescription = stringResource(id = R.string.post_image)
                                                )
                                                Column(
                                                      modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(SpaceLarge)
                                                ) {
                                                      ActionRow(
                                                            isLike = post.isLiked,
                                                            username = post.username,
                                                            modifier = Modifier.fillMaxWidth(),
                                                            onUsernameClick = {
                                                                  onNavigate(Screen.ProfileScreen.route + "?userId=${post.userId}")
                                                            },
                                                            onLikeClick = {
                                                                  viewModel.onEvent(PostDetailEvent.LikePost)
                                                            },
                                                            onCommentClick = {
                                                                  context.showKeyboard()
                                                                  focusRequester.requestFocus()
                                                            },
                                                            onShareClick = {
                                                                  viewModel.onEvent(PostDetailEvent.SharedPost)
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
                                                            fontSize = 16.sp,
                                                            modifier = Modifier.clickable {
                                                                  onNavigate(Screen.PersonListScreen.route + "/${post.id}")
                                                            }
                                                      )
                                                }
                                          }
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
                        Spacer(modifier = Modifier.height(SpaceLarge * 2))
                  }

                  items(state.comments) { comment ->
                        CommentView(
                              context = context,
                              comment = comment,
                              onLikeClick = {
                                    viewModel.onEvent(PostDetailEvent.LikeComment(comment.id))
                              },
                              onLikeByClick = {
                                    onNavigate(Screen.PersonListScreen.route + "/${comment.id}")
                              },
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                          horizontal = SpaceLarge,
                                          vertical = SpaceSmall
                                    )
                        )
                  }
            }
            SendTextField(
                  state = viewModel.commentTextState.value,
                  onValueChange = {
                        viewModel.onEvent(PostDetailEvent.EnteredComment(it))
                  },
                  onSend = {
                        viewModel.onEvent(PostDetailEvent.Comment)
                  },
                  hint = stringResource(id = R.string.comment_hint),
                  isLoading = viewModel.commentState.value.isLoading,
                  focusRequester = focusRequester
            )
      }

}
