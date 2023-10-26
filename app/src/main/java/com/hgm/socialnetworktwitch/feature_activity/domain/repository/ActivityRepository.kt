package com.hgm.socialnetworktwitch.feature_activity.domain.repository

import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import kotlinx.coroutines.flow.Flow


interface ActivityRepository {

      fun getActivitiesForUser(): Flow<PagingData<Activity>>
}