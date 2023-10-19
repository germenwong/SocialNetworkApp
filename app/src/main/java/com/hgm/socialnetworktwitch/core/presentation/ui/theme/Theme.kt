package com.hgm.socialnetworktwitch.core.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
      primary = GreenAccent,
      background = DarkGray,
      onBackground = TextWhite,
      onPrimary = DarkGray,
      surface = MediumGray,
      onSurface = LightGray,
      inverseSurface = LightGray
)

@Composable
fun SocialNetworkTwitchTheme(
      darkTheme: Boolean = isSystemInDarkTheme(),
      content: @Composable () -> Unit
) {
      val view = LocalView.current
      if (!view.isInEditMode) {
            SideEffect {
                  val window = (view.context as Activity).window
                  window.statusBarColor = DarkColorScheme.background.toArgb()
                  window.navigationBarColor = DarkColorScheme.background.toArgb()
                  WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                        darkTheme
            }
      }

      MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = Typography,
            content = content
      )
}