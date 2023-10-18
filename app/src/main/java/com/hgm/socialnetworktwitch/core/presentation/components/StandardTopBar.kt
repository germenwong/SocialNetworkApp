package com.hgm.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.TextWhite

/**
 * @auth：HGM
 * @date：2023-10-11 9:35
 * @desc：
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTopBar(
      navController: NavController,
      modifier: Modifier = Modifier,
      showBackIcon: Boolean = false,
      navAction: @Composable RowScope.() -> Unit = {},
      title: @Composable () -> Unit = {},
) {
      TopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon =  {
                  if (showBackIcon) {
                        IconButton(onClick = {
                              navController.navigateUp()
                        }) {
                              Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = stringResource(id = R.string.back),
                                    tint = MaterialTheme.colorScheme.onBackground
                              )
                        }
                  }
            },
            actions = navAction,
            colors = TopAppBarDefaults.smallTopAppBarColors(
                  navigationIconContentColor = TextWhite,
                  containerColor = Color.Transparent
            ),
      )
}