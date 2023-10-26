package com.hgm.socialnetworktwitch.feature_activity.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hgm.socialnetworktwitch.feature_activity.domain.use_case.GetActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
      private val getActivitiesUseCase: GetActivitiesUseCase
) : ViewModel() {

      private val _state = mutableStateOf(ActivityState())
      val state: State<ActivityState> = _state

      val activities=getActivitiesUseCase().cachedIn(viewModelScope)

      fun onEvent(event: ActivityEvent){
            when (event) {
                  is ActivityEvent.ClickedOnUser -> TODO()
                  is ActivityEvent.ClickedOnParent -> TODO()
            }
      }
}