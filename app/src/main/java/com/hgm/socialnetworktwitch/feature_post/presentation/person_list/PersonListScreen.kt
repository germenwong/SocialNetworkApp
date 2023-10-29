package com.hgm.socialnetworktwitch.feature_post.presentation.person_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.User
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PersonListScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: PersonListViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val state = viewModel.state.value


      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.Navigate -> TODO()
                        is UiEvent.NavigateUp -> TODO()
                        is UiEvent.ShowSnackBar -> {
                              snackBarState.showSnackbar(event.uiText.asString(context))
                        }
                  }
            }
      }


      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  onNavigateUp = onNavigateUp,
                  showBackIcon = true,
                  title = {
                        Text(
                              text = stringResource(id = R.string.liked_by),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            )

            Box(
                  modifier = Modifier.fillMaxSize(),
                  contentAlignment = Alignment.Center
            ) {
                  LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(SpaceLarge)
                  ) {
                        items(state.users) { userItem ->
                              UserProfileItem(
                                    userItem = userItem,
                                    ownUserId = viewModel.ownUserId.value+"1231",
                                    onItemClick = {
                                          onNavigate(Screen.ProfileScreen.route + "?${userItem.userId}")
                                    },
                                    onActionItemClick = {
                                          viewModel.onEvent(
                                                PersonListEvent.UpdateFollowState(
                                                      userItem.userId
                                                )
                                          )
                                    }
                              )
                              Spacer(modifier = Modifier.height(SpaceMedium))
                        }
                  }

                  if (state.isLoading) {
                        CircularProgressIndicator()
                  }
            }
      }
}