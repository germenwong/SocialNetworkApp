package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.User
import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSS
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.feature_profile.presentation.search.SearchEvent


@Composable
fun UserProfileItem(
      context:Context,
      userItem: UserItem,
      ownUserId: String = "",
      onItemClick: () -> Unit = {},
      modifier: Modifier = Modifier,
      onActionItemClick: () -> Unit = {},
) {
      Card(
            modifier = modifier
                  .clickable { onItemClick() },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                  containerColor = MaterialTheme.colorScheme.surface
            )
            //elevation = CardDefaults.cardElevation(4.dp)
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
                              .data(userItem.profilePictureUrl)
                              .crossfade(true)
                              .build(),
                        contentDescription = stringResource(id = R.string.profile_image),
                        modifier = Modifier
                              .size(ProfilePictureSizeSmall)
                              .clip(CircleShape)
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxHeight()
                              .padding(horizontal = SpaceSmall)
                              .weight(1f)
                  ) {
                        Row(
                              verticalAlignment = Alignment.CenterVertically
                        ) {
                              Text(
                                    text = userItem.username,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                          fontWeight = FontWeight.Bold
                                    )
                              )
                              if (userItem.userId == ownUserId) {
                                    Spacer(modifier = Modifier.width(SpaceSmall))
                                    Box(
                                          modifier = Modifier
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(MaterialTheme.colorScheme.onSurface)
                                                .padding(SpaceSS)
                                    ) {
                                          Text(
                                                text = stringResource(id = R.string.me),
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                                          )
                                    }
                              }
                        }
                        Spacer(modifier = Modifier.height(SpaceSS))
                        Text(
                              text = userItem.bio,
                              style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 10.sp
                              ),
                              overflow = TextOverflow.Ellipsis,
                              maxLines = 2
                        )
                  }
                  if (userItem.userId != ownUserId) {
                        IconButton(
                              onClick = onActionItemClick,
                              modifier = Modifier.size(IconSizeMedium)
                        ) {
                              Icon(
                                    imageVector = if (userItem.isFollowing) {
                                          Icons.Default.PersonRemove
                                    } else Icons.Default.PersonAdd,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground,
                              )
                        }
                  }
            }
      }
}
