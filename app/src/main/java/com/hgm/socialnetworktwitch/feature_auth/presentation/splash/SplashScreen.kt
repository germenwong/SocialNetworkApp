package com.hgm.socialnetworktwitch.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.GreenAccent
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.Constants.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext


@Composable
fun SplashScreen(
      onNavigate: (String) -> Unit = {},
      viewModel: SplashViewModel = hiltViewModel(),
      dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
      //动画缩放值
      val scale = remember {
            Animatable(0f)
      }
      //动画插值器
      val overshootInterpolator = remember {
            OvershootInterpolator(2f)
      }


      LaunchedEffect(key1 = true) {
            withContext(dispatcher) {
                  scale.animateTo(
                        targetValue = 0.8f,
                        animationSpec = tween(
                              durationMillis = 500,
                              easing = {
                                    overshootInterpolator.getInterpolation(it)
                              }
                        )
                  )
            }
      }


      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.Navigate -> {
                              delay(SPLASH_SCREEN_DURATION)
                              onNavigate(event.route)
                        }
                        else -> Unit
                  }
            }
      }

      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Icon(
                  tint = GreenAccent,
                  painter = painterResource(id = R.drawable.ic_logo),
                  contentDescription = null,
                  modifier = Modifier.scale(scale.value)
            )
      }
}