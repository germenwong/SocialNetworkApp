package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * @auth：HGM
 * @date：2023-10-21 15:40
 * @desc：
 */
@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

      private val _searchFieldState = mutableStateOf(StandardTextFieldState())
      val searchFieldState: State<StandardTextFieldState> = _searchFieldState

      private val _searchState = mutableStateOf(SearchState())
      val searchState: State<SearchState> = _searchState

      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()

      private var searchJob: Job? = null


      fun onEvent(event: SearchEvent) {
            when (event) {
                  is SearchEvent.Query -> TODO()
                  is SearchEvent.ToggleFollowState -> TODO()
            }
      }
}