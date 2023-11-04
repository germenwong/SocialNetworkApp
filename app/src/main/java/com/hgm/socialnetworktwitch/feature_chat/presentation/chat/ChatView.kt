package com.hgm.socialnetworktwitch.feature_chat.presentation.chat

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSS
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.feature_chat.domain.model.Chat

/**
 * @auth：HGM
 * @date：2023-11-01 15:36
 * @desc：聊天对象组件
 */
@Composable
fun ChatView(
      chat: Chat,
      context: Context,
      modifier: Modifier = Modifier,
      onItemClick: (Chat) -> Unit = {}
) {
      Card(
            modifier = modifier.clickable { onItemClick(chat) },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                  containerColor = MaterialTheme.colorScheme.surface
            )
      ) {
            Row(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(
                              vertical = SpaceSmall,
                              horizontal = SpaceMedium
                        ),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
            ) {
                  AsyncImage(
                        model = ImageRequest.Builder(context)
                              .data(chat.remoteProfilePictureUrl)
                              .crossfade(true)
                              .build(),
                        contentDescription = stringResource(id = R.string.profile_image),
                        modifier = Modifier
                              .size(ProfilePictureSizeMedium)
                              .clip(CircleShape)
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxHeight()
                              .padding(horizontal = SpaceSmall)
                              .weight(1f)
                  ) {
                        Row(
                              modifier=Modifier.fillMaxWidth(),
                              verticalAlignment = Alignment.CenterVertically,
                              horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                              Text(
                                    text = chat.remoteUsername,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                          fontWeight = FontWeight.Bold
                                    )
                              )
                              Text(
                                    text = chat.formattedTime,
                                    style = MaterialTheme.typography.bodyLarge
                              )
                        }
                        Spacer(modifier = Modifier.height(SpaceSS))
                        Text(
                              text = chat.lastMessage,
                              style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 10.sp
                              ),
                              overflow = TextOverflow.Ellipsis,
                              maxLines = 2
                        )
                  }
            }
      }
}