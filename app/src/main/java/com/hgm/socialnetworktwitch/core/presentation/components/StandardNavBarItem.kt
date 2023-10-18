package com.hgm.socialnetworktwitch.core.presentation.components

/**
 * @auth：HGM
 * @date：2023-10-10 14:33
 * @desc：
 */
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.HintGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.StandardNavBarItem(
      icon: ImageVector,
      contentDescription: String? = null,
      selected: Boolean = false,
      enabled: Boolean = true,
      alertCount: Int? = null,
      selectedColor: Color = MaterialTheme.colorScheme.primary,
      unSelectedColor: Color = HintGray,
      onClick: () -> Unit
) {
      // 使用先决函数判断 alertCount，返回false则抛出异常
      alertCount?.let {
            require(alertCount >= 0)
      }

      NavigationBarItem(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = NavigationBarItemDefaults.colors(
                  selectedIconColor = selectedColor,
                  unselectedIconColor =  unSelectedColor,

                  indicatorColor= MaterialTheme.colorScheme.surface,// Item选中时背景颜色
            ),
            icon = {
                  BadgedBox(
                        badge = {
                              if (alertCount != null) {
                                    Badge(
                                          containerColor = MaterialTheme.colorScheme.errorContainer
                                    ) {
                                          Text(text = alertCount.toString())
                                    }
                              }
                        }
                  ) {
                        Icon(
                              imageVector = icon,
                              contentDescription = contentDescription,
                        )
                  }
            }
      )
}
