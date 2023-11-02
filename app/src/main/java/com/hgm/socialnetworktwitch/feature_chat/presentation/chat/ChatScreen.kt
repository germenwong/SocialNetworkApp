package com.hgm.socialnetworktwitch.feature_chat.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.feature_chat.domain.model.ChatItem


@Composable
fun ChatScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {}
) {
      val context = LocalContext.current

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
                  items(15) { i ->
                        ChatView(
                              context = context,
                              chatItem = ChatItem(
                                    username = "Germen Wong",
                                    lastMessage = "你吃饭了没？",
                                    profilePictureUrl = "http://192.168.31.161:8080/profile_pictures/5090e802-3692-4374-970c-2983ff70d3be.jpg",
                                    lastMessageTime = "2023.11.1 10:00"
                              ),
                              onItemClick = {
                                    onNavigate(Screen.MessageScreen.route)
                              }
                        )
                  }
            }
      }
}