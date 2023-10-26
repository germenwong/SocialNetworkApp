package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.AsyncImage
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
      //user: User,
      userItem: UserItem,
      modifier: Modifier = Modifier,
      onItemClick: () -> Unit = {},
      onActionItemClick: () -> Unit = {},
) {
      Card(
            modifier = modifier.clickable { onItemClick() },
            shape = MaterialTheme.shapes.medium,
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
                        model = userItem.profilePictureUrl,
                        contentDescription = stringResource(id = R.string.profile_image),
                        modifier = Modifier
                              .size(ProfilePictureSizeSmall)
                              .clip(CircleShape)
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxHeight()
                              .fillMaxWidth(0.8f)
                              .padding(horizontal = SpaceSmall)
                  ) {
                        Text(
                              text = userItem.username,
                              style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                              )
                        )
                        Spacer(modifier = Modifier.height(SpaceSS))
                        Text(
                              text = userItem.bio,
                              style = MaterialTheme.typography.bodyMedium,
                              overflow = TextOverflow.Ellipsis,
                              maxLines = 2
                        )
                  }
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
