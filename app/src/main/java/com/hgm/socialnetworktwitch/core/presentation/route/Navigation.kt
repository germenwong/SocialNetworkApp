package com.hgm.socialnetworktwitch.core.presentation.route

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.feature_activity.presentation.ActivityScreen
import com.hgm.socialnetworktwitch.feature_chat.presentation.ChatScreen
import com.hgm.socialnetworktwitch.feature_post.presentation.create_post.CreatePostScreen
import com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile.EditProfileScreen
import com.hgm.socialnetworktwitch.feature_auth.presentation.login.LoginScreen
import com.hgm.socialnetworktwitch.feature_post.presentation.main_feed.MainFeedScreen
import com.hgm.socialnetworktwitch.feature_post.presentation.person_list.PersonListScreen
import com.hgm.socialnetworktwitch.feature_post.presentation.post_detail.PostDetailScreen
import com.hgm.socialnetworktwitch.feature_profile.presentation.profile.ProfileScreen
import com.hgm.socialnetworktwitch.feature_auth.presentation.register.RegisterScreen
import com.hgm.socialnetworktwitch.feature_auth.presentation.splash.SplashScreen
import com.hgm.socialnetworktwitch.feature_profile.presentation.search.SearchScreen

/**
 * @auth：HGM
 * @desc：导航主机
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun Navigation(
      paddingValues: PaddingValues,
      navController: NavHostController,
      snackBarState: SnackbarHostState
) {
      NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            modifier = Modifier
                  .fillMaxSize()
                  .padding(paddingValues)
      ) {
            //闪屏页
            composable(Screen.SplashScreen.route) {
                  SplashScreen(
                        onNavigate = { route ->
                              navController.navigate(route) {
                                    popUpTo(Screen.SplashScreen.route) {
                                          inclusive = true
                                    }
                              }
                        }
                  )
            }

            //登录页
            composable(Screen.LoginScreen.route) {
                  LoginScreen(
                        snackBarState = snackBarState,
                        onNavigate = { route ->
                              navController.navigate(route) {
                                    popUpTo(Screen.LoginScreen.route) {
                                          inclusive = true
                                    }
                              }
                        }
                  )
            }

            //注册页
            composable(Screen.RegisterScreen.route) {
                  RegisterScreen(navController = navController, snackBarState = snackBarState)
            }

            //首页
            composable(Screen.MainFeedScreen.route) {
                  MainFeedScreen(
                        onNavigate = navController::navigate,
                        onNavigateUp = navController::navigateUp
                  )
            }

            //聊天页
            composable(Screen.ChatScreen.route) {
                  ChatScreen()
            }

            //个人页
            composable(
                  route = Screen.ProfileScreen.route + "?userId={userId}",
                  arguments = listOf(
                        navArgument("userId") {
                              nullable = true
                              defaultValue = null
                              type = NavType.StringType
                        }
                  )
            ) {
                  ProfileScreen(
                        onNavigate = navController::navigate,
                        userId = it.arguments?.getString("userId"),
                        onLogout = {
                              navController.navigate(Screen.SplashScreen.route) {
                                    //清除给定目标往后的栈
                                    popUpTo(Screen.MainFeedScreen.route) {
                                          inclusive = true//包括该目标
                                    }
                              }
                        }
                  )
            }

            //动态页
            composable(Screen.ActivityScreen.route) {
                  ActivityScreen(onNavigate = navController::navigate)
            }

            //创建帖子页
            composable(Screen.CreatePostScreen.route) {
                  CreatePostScreen(
                        snackBarState = snackBarState,
                        onNavigateUp = navController::navigateUp
                  )
            }

            //帖子详情页
            composable(
                  route = Screen.PostDetailScreen.route + "/{postId}?showKeyboard={showKeyboard}",
                  arguments = listOf(
                        navArgument("postId") {
                              type = NavType.StringType
                        },
                        navArgument("showKeyboard") {
                              type = NavType.BoolType
                              defaultValue = false
                        }
                  ),
                  deepLinks = listOf(
                        navDeepLink {
                              action = Intent.ACTION_VIEW
                              uriPattern = "https://www.coders-hub.com/{postId}"
                        }
                  )
            ) {
                  PostDetailScreen(
                        snackBarState = snackBarState,
                        onNavigate = navController::navigate,
                        onNavigateUp = navController::navigateUp,
                        showKeyboard = it.arguments?.getBoolean("showKeyboard") ?: false
                  )
            }

            //编辑个人页
            composable(
                  route = Screen.EditProfileScreen.route + "/{userId}",
                  arguments = listOf(
                        navArgument("userId") {
                              type = NavType.StringType
                        }
                  )
            ) {
                  EditProfileScreen(
                        snackBarState = snackBarState,
                        onNavigateUp = navController::navigateUp,
                  )
            }

            //用户列表页
            composable(
                  route = Screen.PersonListScreen.route + "/{parentId}",
                  arguments = listOf(
                        navArgument("parentId") {
                              type = NavType.StringType
                        }
                  )
            ) {
                  PersonListScreen(
                        snackBarState = snackBarState,
                        onNavigate = navController::navigate,
                        onNavigateUp = navController::navigateUp
                  )
            }

            //查询用户页
            composable(Screen.SearchScreen.route) {
                  SearchScreen(
                        snackBarState = snackBarState,
                        onNavigate = navController::navigate,
                        onNavigateUp = navController::navigateUp
                  )
            }
      }
}