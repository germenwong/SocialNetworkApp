package com.hgm.socialnetworktwitch.presentation.activity

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.domain.model.Activity
import com.hgm.socialnetworktwitch.domain.util.ActivityAction
import com.hgm.socialnetworktwitch.domain.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.presentation.activity.components.ActivityItem
import com.hgm.socialnetworktwitch.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import kotlin.random.Random

/**
 * @auth：HGM
 * @date：2023-10-10 16:16
 * @desc：
 */
@Composable
fun ActivityScreen(
      navController: NavController,
      viewModel: ActivityViewModel = hiltViewModel()
) {
      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
                  navController = navController,
                  title = {
                        Text(
                              text = stringResource(id = R.string.your_activity),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  },
                  modifier = Modifier.fillMaxWidth(),
            )

            LazyColumn(
                  modifier = Modifier
                        .fillMaxSize(),
                  contentPadding = PaddingValues(SpaceMedium),
                  verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                  items(10) {
                        ActivityItem(
                              activity = Activity(
                                    username = "Anthony",
                                    actionType = if (Random.nextInt(2) == 0) {
                                          ActivityAction.LikedPost
                                    } else {
                                          ActivityAction.CommentOnPost
                                    },
                                    formattedTime = DateFormattedUtil.timestampToString(
                                          timestamp = System.currentTimeMillis(),
                                          pattern = "MMM dd，HH:mm"
                                    )
                              )
                        )
                  }
            }
      }
}