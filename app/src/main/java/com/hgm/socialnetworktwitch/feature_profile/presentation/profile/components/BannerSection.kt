package com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components

/**
 * @auth：HGM
 * @date：2023-10-11 15:28
 * @desc：背景图模块组件
 */
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.util.toPx

@Composable
fun BannerSection(
      modifier: Modifier = Modifier,
      iconSize: Dp = 30.dp,
      imageModifier: Modifier = Modifier,
      leftIconModifier: Modifier = Modifier,
      rightIconModifier: Modifier = Modifier,
      onGithubClick: () -> Unit = {},
      onInstagramClick: () -> Unit = {},
      onLikedInClick: () -> Unit = {},
) {

      BoxWithConstraints(
            modifier = modifier
      ) {
            Image(
                  painter = painterResource(id = R.drawable.channel),
                  contentDescription = stringResource(id = R.string.banner_image),
                  contentScale = ContentScale.Crop,
                  modifier = imageModifier
                        .fillMaxSize()
            )
            Box(
                  modifier = Modifier
                        .fillMaxSize()
                        .background(
                              brush = Brush.verticalGradient(
                                    colors = listOf(
                                          Color.Transparent,
                                          Color.Black
                                    ),
                                    startY = constraints.maxHeight - iconSize.toPx() * 2f
                              )
                        )
            )
            Row(
                  modifier = leftIconModifier
                        .height(iconSize)
                        .align(Alignment.BottomStart)
                        .padding(SpaceSmall)
            ) {
                  Spacer(modifier = Modifier.width(SpaceSmall))
                  Image(
                        painter = painterResource(id = R.drawable.ic_js_logo),
                        contentDescription = "Javscript",
                        modifier = Modifier.height(iconSize)
                  )
                  Spacer(modifier = Modifier.width(SpaceMedium))
                  Image(
                        painter = painterResource(id = R.drawable.ic_csharp_logo),
                        contentDescription = "C#",
                        modifier = Modifier.height(iconSize)
                  )
                  Spacer(modifier = Modifier.width(SpaceMedium))
                  Image(
                        painter = painterResource(id = R.drawable.ic_kotlin_logo),
                        contentDescription = "Kotlin",
                        modifier = Modifier.height(iconSize)
                  )
            }

            Row(
                  modifier = rightIconModifier
                        .height(iconSize)
                        .align(Alignment.BottomEnd)
                        .padding(SpaceSmall)
            ) {
                  IconButton(
                        onClick = onGithubClick,
                        modifier = Modifier.size(iconSize)
                  ) {
                        Image(
                              painter = painterResource(id = R.drawable.ic_github_icon_1),
                              contentDescription = "GitHub",
                              modifier = Modifier.size(iconSize)
                        )
                  }
                  IconButton(
                        onClick = onInstagramClick,
                        modifier = Modifier.size(iconSize)
                  ) {
                        Image(
                              painter = painterResource(id = R.drawable.ic_instagram_glyph_1),
                              contentDescription = "Instagram",
                              modifier = Modifier.size(iconSize)
                        )
                  }
                  IconButton(
                        onClick = onLikedInClick,
                        modifier = Modifier.size(iconSize)
                  ) {
                        Image(
                              painter = painterResource(id = R.drawable.ic_linkedin_icon_1),
                              contentDescription = "LinkedIn",
                              modifier = Modifier.size(iconSize)
                        )
                  }
            }
      }
}