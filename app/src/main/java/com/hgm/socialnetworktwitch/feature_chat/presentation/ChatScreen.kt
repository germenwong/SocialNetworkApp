package com.hgm.socialnetworktwitch.feature_chat.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

/**
 * @auth：HGM
 * @date：2023-10-10 16:14
 * @desc：
 */
@Composable
fun ChatScreen(
      navController: NavController
) {
      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Text(text = "Chat")
      }
}