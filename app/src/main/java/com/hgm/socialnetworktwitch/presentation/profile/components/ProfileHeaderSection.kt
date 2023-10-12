package com.hgm.socialnetworktwitch.presentation.profile.components

/**
 * @auth：HGM
 * @date：2023-10-11 16:30
 * @desc：个人信息组件
 */
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.User
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceSmall

@Composable
fun ProfileHeaderSection(
      user: User,
      modifier: Modifier = Modifier,
      isOwnProfile: Boolean = true,
      onEditClick: () -> Unit={}
) {
      Column(
            modifier = modifier
                  .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
      ) {

            Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                        .offset(
                              x =
                              if (isOwnProfile) {
                                    (30.dp + SpaceSmall) / 2f
                              } else 0.dp
                        )
            ) {
                  Text(
                        text = user.username,
                        style = MaterialTheme.typography.displayLarge.copy(
                              fontSize = 24.sp
                        ),
                        textAlign = TextAlign.Center,
                  )
                  if (isOwnProfile) {
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        IconButton(
                              onClick = onEditClick,
                              modifier = Modifier.size(30.dp)
                        ) {
                              Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(id = R.string.edit)
                              )
                        }
                  }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(
                  text = user.description,
                  style = MaterialTheme.typography.bodyMedium,
                  textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            ProfileStats(user = user, isOwnProfile = isOwnProfile)
      }

}
