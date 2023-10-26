package com.hgm.socialnetworktwitch.feature_activity.domain.use_case

import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import com.hgm.socialnetworktwitch.feature_activity.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow


class GetActivitiesUseCase(
      private val repository: ActivityRepository
) {

      operator fun invoke():Flow<PagingData<Activity>> {
            return repository.getActivitiesForUser()
      }
}