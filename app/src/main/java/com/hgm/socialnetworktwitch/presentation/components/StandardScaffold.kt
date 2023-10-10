package com.hgm.socialnetworktwitch.presentation.components

/**
 * @auth：HGM
 * @date：2023-10-10 15:10
 * @desc：
 */
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.BottomNavItem
import com.hgm.socialnetworktwitch.presentation.ui.theme.MediumGray
import com.hgm.socialnetworktwitch.presentation.util.Screen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
      navController: NavController,
      modifier: Modifier = Modifier,
      showBottomBar: Boolean = true,
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
                  alertCount = 42
            ),
            BottomNavItem(
                  route = Screen.ProfileScreen.route,
                  icon = Icons.Default.Person,
                  contentDescription = "Profile"
            ),
      ),
      onFabClick: () -> Unit = {},
      content: @Composable () -> Unit
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
                                          item.route == navController.currentDestination?.route

                                    StandardNavBarItem(
                                          icon = item.icon,
                                          contentDescription = item.contentDescription,
                                          selected = selected,
                                          alertCount = item.alertCount
                                    ) {
                                          if (!selected) {
                                                navController.navigate(item.route)
                                          }
                                    }
                              }
                        }
                  }
            },
            floatingActionButton = {
                  if (showBottomBar) {
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
            modifier = modifier
      ) {
            content()
      }
}
