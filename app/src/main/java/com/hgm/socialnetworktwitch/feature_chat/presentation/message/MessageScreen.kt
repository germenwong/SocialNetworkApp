package com.hgm.socialnetworktwitch.feature_chat.presentation.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.SendTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Message
import com.hgm.socialnetworktwitch.feature_chat.presentation.message.components.OwnMessage
import com.hgm.socialnetworktwitch.feature_chat.presentation.message.components.RemoteMessage


@Composable
fun MessageScreen(
      remoteUsername: String,
      remoteUserProfilePictureUrl: String,
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: MessageViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val pagingState = viewModel.pagingState.value
      val focusRequester = remember {
            FocusRequester()
      }

      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  showBackIcon = true,
                  onNavigateUp = onNavigateUp,
                  title = {
                        Text(text = remoteUsername)
                  }
            )

            LazyColumn(
                  modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(SpaceMedium),
                  verticalArrangement = Arrangement.spacedBy(SpaceLarge)
            ) {
                  items(pagingState.items.size) { index ->
                        val message = pagingState.items[index]
                        //满足刷新下一页的条件：列表前一位、数据没有到底、不在刷新状态
                        if (index >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                              viewModel.loadNextMessages()
                        }
                        RemoteMessage(
                              color = MaterialTheme.colorScheme.surface,
                              message = message.text,
                              formattedTime = message.formattedTime
                        )
                        OwnMessage(
                              color = MaterialTheme.colorScheme.primary,
                              message = message.text,
                              formattedTime = message.formattedTime
                        )
                  }
            }

            SendTextField(
                  state = viewModel.messageTextFieldState.value,
                  onValueChange = {
                        viewModel.onEvent(MessageEvent.EnterMessage(it))
                  },
                  onSend = {
                        viewModel.onEvent(MessageEvent.SendMessage)
                  },
                  hint = stringResource(id = R.string.comment_hint),
                  //focusRequester = focusRequester
            )
      }
}