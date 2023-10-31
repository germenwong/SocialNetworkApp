package com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components

/**
 * @auth：HGM
 * @date：2023-10-11 15:28
 * @desc：背景图模块组件
 */
import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.util.toPx

@Composable
fun BannerSection(
      context: Context,
      bannerUrl: String?,
      iconSize: Dp = 35.dp,
      topSkillUrls: List<String>,
      hasGithub: Boolean = false,
      hasLinkedIn: Boolean = false,
      hasInstagram: Boolean = false,
      onGithubClick: () -> Unit = {},
      onLinkedInClick: () -> Unit = {},
      onInstagramClick: () -> Unit = {},
      modifier: Modifier = Modifier,
      imageModifier: Modifier = Modifier,
      leftIconModifier: Modifier = Modifier,
      rightIconModifier: Modifier = Modifier,
) {

      BoxWithConstraints(
            modifier = modifier
      ) {
            AsyncImage(
                  model = ImageRequest.Builder(context)
                        .data(bannerUrl)
                        .crossfade(true)
                        .build(),
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
                  topSkillUrls.forEach { skillUrl ->
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        AsyncImage(
                              model = ImageRequest.Builder(context)
                                    .data(skillUrl)
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                              contentDescription = null,
                              modifier = Modifier.height(iconSize)
                        )
                  }
            }

            Row(
                  modifier = rightIconModifier
                        .height(iconSize)
                        .align(Alignment.BottomEnd)
                        .padding(SpaceSmall)
            ) {
                  if (hasGithub) {
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
                  }
                  if (hasInstagram) {
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
                  }
                  if (hasLinkedIn) {
                        IconButton(
                              onClick = onLinkedInClick,
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
}