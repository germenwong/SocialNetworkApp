package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.PostEvent
import com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.component.PostView
import kotlinx.coroutines.flow.collectLatest


@Composable
fun MainFeedScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: MainFeedViewModel = hiltViewModel()
) {
      val pagingState = viewModel.pagingState.value

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is PostEvent.Refresh -> {

                        }
                  }
            }
      }

      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  modifier = Modifier.fillMaxWidth(),
                  onNavigateUp = onNavigateUp,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_feed),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  },
                  navAction = {
                        IconButton(onClick = {
                              onNavigate(Screen.SearchScreen.route)
                        }) {
                              Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search_post),
                                    tint = MaterialTheme.colorScheme.onBackground
                              )
                        }
                  }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                  LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                  ) {
                        items(pagingState.items.size) { index ->
                              val post = pagingState.items[index]
                              //满足刷新下一页的条件：列表前一位、数据没有到底、不在刷新状态
                              if (index >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                                    viewModel.loadNextItems()
                              }
                              PostView(
                                    post = post,
                                    //showProfileImage = true,
                                    onPostClick = { onNavigate(Screen.PostDetailScreen.route + "/${post.id}") },
                                    onLikeClick = { viewModel.onEvent(MainFeedEvent.LikePost(post.id)) }
                              )
                        }
                  }
                  if (pagingState.isLoading){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                  }
            }
      }
}