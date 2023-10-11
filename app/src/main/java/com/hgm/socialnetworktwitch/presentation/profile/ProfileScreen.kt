package com.hgm.socialnetworktwitch.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium

/**
 * @auth：HGM
 * @date：2023-10-10 16:14
 * @desc：
 */
@Composable
fun ProfileScreen(
      navController: NavController
) {
      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  navController = navController,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_profile),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  },
                  modifier = Modifier.fillMaxWidth(),
            )

            LazyColumn(
                  modifier = Modifier.fillMaxSize(),
                  contentPadding = PaddingValues(SpaceMedium),
                  verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                  items(10) {

                  }
            }
      }
}