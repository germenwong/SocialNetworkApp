package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.feature_post.presentation.person_list.UserProfileItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoilApi
@Composable
fun SearchScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: SearchViewModel = hiltViewModel()
) {
      val state = viewModel.searchState.value
      val context = LocalContext.current

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.ShowSnackBar -> {
                              snackBarState.showSnackbar(message = event.uiText.asString(context))
                        }

                        else -> Unit
                  }
            }
      }


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
                                    viewModel.onEvent(SearchEvent.Query(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceLarge))
                        LazyColumn(
                              modifier = Modifier.fillMaxSize()
                        ) {
                              items(state.userItems) { userItem ->
                                    UserProfileItem(
                                          context = context,
                                          userItem = userItem,
                                          ownUserId = viewModel.ownUserId.value,
                                          onItemClick = {
                                                onNavigate(
                                                      Screen.ProfileScreen.route + "?userId=${userItem.userId}"
                                                )
                                          },
                                          onActionItemClick = {
                                                viewModel.onEvent(
                                                      SearchEvent.UpdateFollowState(
                                                            userItem.userId
                                                      )
                                                )
                                          }
                                    )
                                    Spacer(modifier = Modifier.height(SpaceMedium))
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