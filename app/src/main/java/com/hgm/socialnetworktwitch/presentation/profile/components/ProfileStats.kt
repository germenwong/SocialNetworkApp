package com.hgm.socialnetworktwitch.presentation.profile.components

/**
 * @auth：HGM
 * @date：2023-10-11 17:13
 * @desc：个人信息的统计数据
 */
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.User
import com.hgm.socialnetworktwitch.presentation.ui.theme.RoundedCornerSmall
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.presentation.ui.theme.TextWhite

@Composable
fun ProfileStats(
      user: User,
      modifier: Modifier = Modifier,
      isFollowing: Boolean = true,
      isOwnProfile: Boolean = true,
      onFollowClick: () -> Unit = {}
) {
      Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
      ) {
            ProfileNumber(
                  number = user.followerCount,
                  text = stringResource(id = R.string.follower)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            ProfileNumber(
                  number = user.followingCount,
                  text = stringResource(id = R.string.following)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            ProfileNumber(
                  number = user.postCount,
                  text = stringResource(id = R.string.posts)
            )
            if (isOwnProfile) {
                  Spacer(modifier = Modifier.width(SpaceMedium))
                  Button(
                        onClick = { onFollowClick() },
                        colors = ButtonDefaults.buttonColors(
                              containerColor = if (isFollowing) Color.Red else MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(RoundedCornerSmall),
                        contentPadding = PaddingValues(SpaceSmall)
                  ) {
                        Text(
                              text = if (isFollowing) {
                                    stringResource(id = R.string.unfollow)
                              } else {
                                    stringResource(id = R.string.following)
                              },
                              color = if (isFollowing) {
                                    Color.White
                              } else MaterialTheme.colorScheme.onPrimary
                        )
                  }
            }
      }
}
