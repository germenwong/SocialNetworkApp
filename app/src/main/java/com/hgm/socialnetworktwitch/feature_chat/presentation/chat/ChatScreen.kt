package com.hgm.socialnetworktwitch.feature_chat.presentation.chat

import android.util.Base64
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ChatScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: ChatViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val state = viewModel.state.value
      println("Screen：${state.chats.size}")

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.Navigate -> {
                              onNavigate(event.route)
                        }

                        is UiEvent.ShowSnackBar -> {
                              snackBarState.showSnackbar(event.uiText.asString(context))
                        }

                        else -> Unit
                  }
            }
      }


      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  modifier = Modifier.fillMaxWidth(),
                  onNavigateUp = onNavigateUp,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_chat),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            )

            LazyColumn(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(SpaceMedium),
                  verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                  items(state.chats) { chat ->
                        ChatView(
                              context = context,
                              chat = chat,
                              onItemClick = {
                                    onNavigate(
                                          Screen.MessageScreen.route + "/${chat.chatId}/${chat.remoteUserId}/${chat.remoteUsername}/${Base64.encodeToString(chat.remoteProfilePictureUrl.encodeToByteArray(),0)}"
                                    )
                              }
                        )
                  }
            }
      }
}