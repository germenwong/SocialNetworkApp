package com.hgm.socialnetworktwitch.feature_activity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_activity.data.paging.ActivityPagingSource
import com.hgm.socialnetworktwitch.feature_activity.data.remote.ActivityApi
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import com.hgm.socialnetworktwitch.feature_activity.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow


class ActivityRepositoryImpl(
      private val api: ActivityApi
) : ActivityRepository {

      override fun getActivitiesForUser(): Flow<PagingData<Activity>> {
            return Pager(
                  config = PagingConfig(pageSize = Constants.PAGE_DEFAULT_SIZE)
            ) {
                  ActivityPagingSource(api)
            }.flow
      }
}