package com.hgm.socialnetworktwitch.core.presentation.components

/**
 * @auth：HGM
 * @date：2023-10-10 15:10
 * @desc：
 */
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.model.BottomNavItem
import com.hgm.socialnetworktwitch.core.presentation.route.Screen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
      navController: NavController,
      modifier: Modifier = Modifier,
      snackBarState:SnackbarHostState,
      showBottomBar: Boolean = true,
      showFAB: Boolean = true,
      bottomNavItems: List<BottomNavItem> = listOf(
            BottomNavItem(
                  route = Screen.MainFeedScreen.route,
                  icon = Icons.Default.Home,
                  contentDescription = "Home",
            ),
            BottomNavItem(
                  route = Screen.ChatScreen.route,
                  icon = Icons.Default.Chat,
                  contentDescription = "Chat"
            ),
            BottomNavItem(
                  route = Screen.ActivityScreen.route,
                  icon = Icons.Default.Notifications,
                  contentDescription = "Activity",
                  alertCount = 3
            ),
            BottomNavItem(
                  route = Screen.ProfileScreen.route,
                  icon = Icons.Default.Person,
                  contentDescription = "Profile"
            ),
      ),
      onFabClick: () -> Unit = {},
      content: @Composable (padding: PaddingValues) -> Unit
) {
      Scaffold(
            bottomBar = {
                  if (showBottomBar) {
                        NavigationBar(
                              containerColor = Color.Transparent
                        ) {
                              bottomNavItems.forEachIndexed { index, item ->
                                    // 判断当前Item路由是否和控制器路由一致
                                    val selected =
                                          navController.currentDestination?.route?.startsWith(item.route) == true
                                    StandardNavBarItem(
                                          icon = item.icon,
                                          selected = selected,
                                          alertCount = item.alertCount,
                                          contentDescription = item.contentDescription
                                    ) {
                                          navController.navigate(item.route) {
                                                // 弹出到主页起始页面，以避免当用户选择项目时在返回堆栈上建立一大堆目的地
                                                popUpTo(/*navController.graph.findStartDestination().id*/
                                                      Screen.MainFeedScreen.route
                                                ) {
                                                      saveState = true// 保存状态
                                                }
                                                launchSingleTop = true// 重新选择同一项目时避免同一目的地的多个副本
                                                restoreState = true // 重新选择先前选择的项目时恢复状态
                                          }
                                    }
                              }
                        }
                  }
            },
            floatingActionButton = {
                  if (showFAB) {
                        FloatingActionButton(
                              containerColor = MaterialTheme.colorScheme.primary,
                              onClick = onFabClick,
                        ) {
                              Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.make_post)
                              )
                        }
                  }
            },
            floatingActionButtonPosition = FabPosition.End,
            snackbarHost = { SnackbarHost(hostState = snackBarState)},
            modifier = modifier,
      ) {
            content(it)
      }
}
