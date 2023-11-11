package com.hgm.socialnetworktwitch.feature_profile.presentation.profile

import android.util.Base64
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.User
import com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component.PostView
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components.BannerSection
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.PostEvent
import com.hgm.socialnetworktwitch.core.util.sharePostIntent
import com.hgm.socialnetworktwitch.core.util.toPx
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ProfileScreen(
      userId: String? = null,
      onLogout: () -> Unit = {},
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: ProfileViewModel = hiltViewModel(),
      profilePictureSize: Dp = ProfilePictureSizeLarge
) {
      val context = LocalContext.current
      val lazyListState = rememberLazyListState()
      val toolbarState = viewModel.toolbarState.value
      val iconHorizontalCenterLength =
            (LocalConfiguration.current.screenWidthDp.dp.toPx() / 4f -
                    (profilePictureSize / 4f).toPx() -
                    SpaceSmall.toPx()) / 2f
      val iconSizeExpanded = 35.dp
      val toolbarHeightCollapsed = 75.dp
      val imageCollapsedOffsetY = remember {
            (toolbarHeightCollapsed - profilePictureSize / 2f) / 2f
      }
      val iconCollapsedOffsetY = remember {
            (toolbarHeightCollapsed - iconSizeExpanded) / 2f
      }
      val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
      val toolbarHeightExpanded = remember {
            bannerHeight + profilePictureSize
      }
      val maxOffset = remember {
            toolbarHeightExpanded - toolbarHeightCollapsed
      }
      val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                  override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                        val delta = available.y
                        //不能滚动的情况
                        val shouldNotScroll =
                              delta > 0f && lazyListState.firstVisibleItemIndex != 0 || viewModel.pagingState.value.items.isEmpty()
                        if (shouldNotScroll) {
                              return Offset.Zero
                        }
                        val newOffset = viewModel.toolbarState.value.toolbarOffsetY + delta
                        viewModel.setToolbarOffsetY(
                              newOffset.coerceIn(
                                    minimumValue = -maxOffset.toPx(),
                                    maximumValue = 0f
                              )
                        )
                        viewModel.setExpandedRatio((viewModel.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                        return Offset.Zero
                  }
            }
      }
      val state = viewModel.state.value
      val pagingState = viewModel.pagingState.value


      LaunchedEffect(key1 = true) {
            viewModel.getProfile(userId)
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is PostEvent.Refresh -> {

                        }
                  }
            }
      }



      Box(
            modifier = Modifier
                  .fillMaxSize()
                  .nestedScroll(nestedScrollConnection)
      ) {
            LazyColumn(
                  modifier = Modifier
                        .fillMaxSize(),
                  state = lazyListState
            ) {
                  item {
                        Spacer(
                              modifier = Modifier.height(
                                    toolbarHeightExpanded - profilePictureSize / 2f
                              )
                        )
                  }
                  item {
                        state.profile?.let { profile ->
                              ProfileHeaderSection(
                                    user = User(
                                          userId = profile.userId,
                                          profilePictureUrl = profile.profilePictureUrl,
                                          username = profile.username,
                                          description = profile.bio,
                                          followingCount = profile.followingCount,
                                          followedCount = profile.followedCount,
                                          postCount = profile.postCount
                                    ),
                                    isFollowing = profile.isFollowing,
                                    isOwnProfile = profile.isOwnProfile,
                                    onEditClick = {
                                          onNavigate(Screen.EditProfileScreen.route + "/${profile.userId}")
                                    },
                                    onLogoutClick = {
                                          viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                                    },
                                    onMessageClick = {
                                          val profilePictureUrl = Base64.encodeToString(
                                                profile.profilePictureUrl.encodeToByteArray(),
                                                0
                                          )
                                          onNavigate(
                                                Screen.MessageScreen.route + "/$userId/${profile.username}/$profilePictureUrl"
                                          )
                                    }
                              )
                        }
                  }
                  items(pagingState.items.size) { index ->
                        val post = pagingState.items[index]
                        //满足刷新下一页的条件：列表前一位、数据没有到底、不在刷新状态
                        if (index >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                              viewModel.loadNextPosts()
                        }

                        PostView(
                              post = post,
                              context = context,
                              //showProfileImage = false,
                              onPostClick = {
                                    onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                              },
                              onLikeClick = {
                                    viewModel.onEvent(ProfileEvent.LikePost(post.id))
                              },
                              onCommentClick = {
                                    onNavigate(Screen.PostDetailScreen.route + "/${post.id}?showKeyboard=true")
                              },
                              onShareClick = {
                                    context.sharePostIntent(post.id)
                              },
                              onDeleteClick = {
                                    viewModel.onEvent(ProfileEvent.DeletePost(post.id))
                              }
                        )
                  }
            }
            Column(
                  modifier = Modifier
                        .align(Alignment.TopCenter)
            ) {
                  state.profile?.let { profile ->
                        BannerSection(
                              context = context,
                              modifier = Modifier
                                    .height(
                                          (bannerHeight * toolbarState.expandedRatio).coerceIn(
                                                minimumValue = toolbarHeightCollapsed,
                                                maximumValue = bannerHeight
                                          )
                                    ),
                              leftIconModifier = Modifier
                                    .graphicsLayer {
                                          translationY = (1f - toolbarState.expandedRatio) *
                                                  -iconCollapsedOffsetY.toPx()
                                          translationX = (1f - toolbarState.expandedRatio) *
                                                  iconHorizontalCenterLength
                                    },
                              rightIconModifier = Modifier
                                    .graphicsLayer {
                                          translationY = (1f - toolbarState.expandedRatio) *
                                                  -iconCollapsedOffsetY.toPx()
                                          translationX = (1f - toolbarState.expandedRatio) *
                                                  -iconHorizontalCenterLength
                                    },
                              bannerUrl = profile.bannerUrl,
                              topSkillUrls = profile.topSkills.map { it.imageUrl },
                              hasGithub = !profile.gitHubUrl.isNullOrBlank(),
                              hasInstagram = !profile.instagramUrl.isNullOrEmpty(),
                              hasLinkedIn = !profile.linkedInUrl.isNullOrEmpty(),
                        )
                        AsyncImage(
                              model = ImageRequest.Builder(context)
                                    .data(profile.profilePictureUrl)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                              contentDescription = stringResource(id = R.string.profile_image),
                              modifier = Modifier
                                    .align(CenterHorizontally)
                                    .graphicsLayer {
                                          translationY = -profilePictureSize.toPx() / 2f -
                                                  (1f - toolbarState.expandedRatio) * imageCollapsedOffsetY.toPx()
                                          transformOrigin = TransformOrigin(
                                                pivotFractionX = 0.5f,
                                                pivotFractionY = 0f
                                          )
                                          val scale = 0.5f + toolbarState.expandedRatio * 0.5f
                                          scaleX = scale
                                          scaleY = scale
                                    }
                                    .size(profilePictureSize)
                                    .clip(CircleShape)
                                    .border(
                                          width = 1.dp,
                                          color = MaterialTheme.colorScheme.onSurface,
                                          shape = CircleShape
                                    )
                        )
                  }
            }


            if (state.isShowLogoutDialog) {
                  AlertDialog(
                        containerColor = MaterialTheme.colorScheme.background,
                        onDismissRequest = { viewModel.onEvent(ProfileEvent.DismissLogoutDialog) },
                        text = {
                              Text(
                                    text = stringResource(id = R.string.are_you_should_logout),
                                    color = MaterialTheme.colorScheme.onBackground
                              )
                        },
                        confirmButton = {
                              TextButton(
                                    onClick = {
                                          viewModel.onEvent(ProfileEvent.Logout)
                                          viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                          onLogout()
                                    }
                              ) {
                                    Text(
                                          text = stringResource(id = R.string.confirm),
                                          color = MaterialTheme.colorScheme.errorContainer
                                    )
                              }
                        },
                        dismissButton = {
                              TextButton(
                                    onClick = { viewModel.onEvent(ProfileEvent.DismissLogoutDialog) }
                              ) {
                                    Text(
                                          text = stringResource(id = R.string.cancel),
                                          color = MaterialTheme.colorScheme.onBackground
                                    )
                              }
                        }
                  )
            }
      }
}