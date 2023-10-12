package com.hgm.socialnetworktwitch.presentation.route

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hgm.socialnetworktwitch.domain.model.Post
import com.hgm.socialnetworktwitch.presentation.activity.ActivityScreen
import com.hgm.socialnetworktwitch.presentation.chat.ChatScreen
import com.hgm.socialnetworktwitch.presentation.create_post.CreatePostScreen
import com.hgm.socialnetworktwitch.presentation.edit_profile.EditProfileScreen
import com.hgm.socialnetworktwitch.presentation.login.LoginScreen
import com.hgm.socialnetworktwitch.presentation.main_feed.MainFeedScreen
import com.hgm.socialnetworktwitch.presentation.person_list.PersonListScreen
import com.hgm.socialnetworktwitch.presentation.post_detail.PostDetailScreen
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
            startDestination = Screen.CreatePostScreen.route,
            modifier = Modifier
                  .fillMaxSize()
                  .padding(paddingValues)
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

            composable(Screen.PostDetailScreen.route){
                  PostDetailScreen(
                        navController = navController,
                        post = Post(
                              username = "Germen Wong",
                              imageUrl = "",
                              profilePictureUrl = "",
                              description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed" +
                                      "diam nonumy eirmod tempor invidunt ut labore et dolore " +
                                      "magna aliquyam erat...",
                              likeCount = 14,
                              commentCount = 53
                        )
                  )
            }

            composable(Screen.EditProfileScreen.route){
                  EditProfileScreen(navController=navController)
            }

            composable(Screen.PersonListScreen.route){
                  PersonListScreen(navController = navController)
            }
      }
}