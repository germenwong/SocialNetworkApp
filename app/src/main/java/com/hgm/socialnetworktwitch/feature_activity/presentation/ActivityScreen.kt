package com.hgm.socialnetworktwitch.feature_activity.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import com.hgm.socialnetworktwitch.feature_activity.domain.model.ActivityType
import com.hgm.socialnetworktwitch.feature_activity.presentation.components.ActivityItem


@Composable
fun ActivityScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      viewModel: ActivityViewModel = hiltViewModel()
) {
      val state = viewModel.state.value
      val activities = viewModel.activities.collectAsLazyPagingItems()

      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
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
                        items(activities) { activity ->
                              activity?.let {
                                    ActivityItem(
                                          Activity(
                                                userId = activity.userId,
                                                username = activity.username,
                                                parentId = activity.parentId,
                                                activityType = activity.activityType,
                                                formattedTime = activity.formattedTime
                                          )
                                    )
                              }
                        }
                  }
            }
            if (state.isLoading) {
                  CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
      }
}