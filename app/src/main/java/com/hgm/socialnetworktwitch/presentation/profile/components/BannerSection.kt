package com.hgm.socialnetworktwitch.presentation.profile.components

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.util.toPx

@Composable
fun BannerSection(
      modifier: Modifier = Modifier,
      iconSize: Dp = 30.dp,
      onGithubClick: () -> Unit = {},
      onInstagramClick: () -> Unit = {},
      onLikedInClick: () -> Unit = {},
) {
      BoxWithConstraints(
            modifier = modifier
      ) {
            Image(
                  painter = painterResource(id = R.drawable.channelart),
                  contentDescription = stringResource(id = R.string.banner_image),
                  modifier = Modifier
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
                  modifier = Modifier
                        .height(iconSize)
                        .align(Alignment.BottomStart)
                        .padding(SpaceSmall)
            ) {
                  Image(
                        painter = painterResource(id = R.drawable.ic_js_logo),
                        contentDescription = "js",
                        modifier = Modifier.size(iconSize)
                  )
                  Image(
                        painter = painterResource(id = R.drawable.ic_csharp_logo),
                        contentDescription = "c#",
                        modifier = Modifier.size(iconSize)
                  )
                  Image(
                        painter = painterResource(id = R.drawable.ic_kotlin_logo),
                        contentDescription = "kotlin",
                        modifier = Modifier.size(iconSize)
                  )
            }

            Row(
                  modifier = Modifier
                        .height(iconSize)
                        .align(Alignment.BottomEnd)
                        .padding(SpaceSmall)
            ) {
                  IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(iconSize)
                  ) {
                        Icon(
                              painter = painterResource(id = R.drawable.ic_github_icon_1),
                              contentDescription = "github"
                        )
                  }
                  IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(iconSize)
                  ) {
                        Icon(
                              painter = painterResource(id = R.drawable.ic_instagram_glyph_1),
                              contentDescription = "ins"
                        )
                  }
                  IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(iconSize)
                  ) {
                        Icon(
                              painter = painterResource(id = R.drawable.ic_linkedin_icon_1),
                              contentDescription = "linkin"
                        )
                  }
            }
      }


}
