package com.hgm.socialnetworktwitch.presentation.create_post

/**
 * @auth：HGM
 * @date：2023-10-10 18:20
 * @desc：
 */
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun CreatePostScreen(
      navController: NavController
) {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
          Text(text = "Create post")
      }
}
