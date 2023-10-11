package com.hgm.socialnetworktwitch.presentation.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hgm.socialnetworktwitch.presentation.activity.ActivityScreen
import com.hgm.socialnetworktwitch.presentation.chat.ChatScreen
import com.hgm.socialnetworktwitch.presentation.create_post.CreatePostScreen
import com.hgm.socialnetworktwitch.presentation.login.LoginScreen
import com.hgm.socialnetworktwitch.presentation.main_feed.MainFeedScreen
import com.hgm.socialnetworktwitch.presentation.profile.ProfileScreen
import com.hgm.socialnetworktwitch.presentation.register.RegisterScreen
import com.hgm.socialnetworktwitch.presentation.splash.SplashScreen

/**
 * @auth：HGM
 * @date：2023-09-22 13:58
 * @desc：
 */
@Composable
fun Navigation(
      navController: NavHostController,
      paddingValues:PaddingValues
) {
      NavHost(
            navController = navController,
            startDestination = Screen.MainFeedScreen.route,
            modifier = Modifier.fillMaxSize().padding(paddingValues)
      ) {
            composable(Screen.SplashScreen.route) {
                  SplashScreen(navController = navController)
            }

            composable(Screen.LoginScreen.route) {
                  LoginScreen(navController = navController)
            }

            composable(Screen.RegisterScreen.route) {
                  RegisterScreen(navController = navController)
            }

            composable(Screen.MainFeedScreen.route) {
                  MainFeedScreen(navController = navController)
            }

            composable(Screen.ChatScreen.route) {
                  ChatScreen(navController = navController)
            }

            composable(Screen.ProfileScreen.route) {
                  ProfileScreen(navController = navController)
            }

            composable(Screen.ActivityScreen.route) {
                  ActivityScreen(navController = navController)
            }

            composable(Screen.CreatePostScreen.route) {
                  CreatePostScreen(navController = navController)
            }
      }
}