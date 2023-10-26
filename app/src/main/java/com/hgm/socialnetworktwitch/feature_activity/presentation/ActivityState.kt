package com.hgm.socialnetworktwitch.feature_activity.presentation

import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity


data class ActivityState(
      val activities: List<Activity> = emptyList(),
      val isLoading: Boolean = false
)
