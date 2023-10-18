package com.hgm.socialnetworktwitch.feature_post.presentation.create_post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @auth：HGM
 * @date：2023-10-12 17:10
 * @desc：
 */
@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {

      private val _descriptionState = mutableStateOf(StandardTextFieldState())
      val descriptionState: State<StandardTextFieldState> = _descriptionState

      fun setDescriptionState(state: StandardTextFieldState) {
            _descriptionState.value = state
      }
}