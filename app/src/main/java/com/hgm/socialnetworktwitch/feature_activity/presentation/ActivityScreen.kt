package com.hgm.socialnetworktwitch.feature_activity.presentation

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
import com.hgm.socialnetworktwitch.feature_activity.domain.Activity
import com.hgm.socialnetworktwitch.feature_activity.domain.ActivityAction
import com.hgm.socialnetworktwitch.core.util.DateFormattedUtil
import com.hgm.socialnetworktwitch.feature_activity.presentation.components.ActivityItem
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import kotlin.random.Random


@Composable
fun ActivityScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: ActivityViewModel = hiltViewModel()
) {
      Column(
            modifier = Modifier.fillMaxSize(),
      ) {
            StandardTopBar(
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