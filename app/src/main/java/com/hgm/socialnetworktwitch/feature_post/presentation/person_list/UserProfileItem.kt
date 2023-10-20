package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

import androidx.compose.foundation.Image
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.User
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall

/**
 * @auth：HGM
 * @date：2023-10-12 16:40
 * @desc：
 */
@Composable
fun UserProfileItem(
      user: User,
      modifier: Modifier = Modifier,
      actionIcon: @Composable () -> Unit = {},
      onItemClick: () -> Unit = {},
      onActionItemClick: () -> Unit = {}
) {
      Card(
            modifier = modifier.clickable { onItemClick() },
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(5.dp)
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
                  Image(
                        painter = painterResource(id = R.drawable.germen),
                        contentDescription = null,
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
                              text = user.username,
                              style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                              )
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))
                        Text(
                              text = user.description,
                              style = MaterialTheme.typography.bodyMedium,
                              overflow = TextOverflow.Ellipsis,
                              maxLines = 2
                        )
                  }
                  IconButton(
                        onClick = onActionItemClick,
                        modifier = Modifier.size(IconSizeMedium)

                  ) {
                        actionIcon()
                  }
            }
      }
}