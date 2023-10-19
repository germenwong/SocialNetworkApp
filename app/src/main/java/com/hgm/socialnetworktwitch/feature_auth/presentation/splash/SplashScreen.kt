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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.GreenAccent
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.Constants.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

/**
 * @auth：HGM
 * @date：2023-09-22 14:17
 * @desc：
 */
@Composable
fun SplashScreen(
      navController: NavController,
      viewModel:SplashViewModel= hiltViewModel()
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


      LaunchedEffect(key1 = true){
            viewModel.eventFlow.collectLatest { event->
                  when (event) {
                        is UiEvent.Navigate -> {
                              delay(SPLASH_SCREEN_DURATION)
                              navController.navigate(event.route){
                                    popUpTo(Screen.SplashScreen.route){
                                          inclusive=true
                                    }
                              }
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
                  painter = painterResource(id = R.drawable.ic_logo),
                  contentDescription = "Logo",
                  tint = GreenAccent,
                  modifier = Modifier.scale(scale.value)
            )
      }


}