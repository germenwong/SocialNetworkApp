package com.hgm.socialnetworktwitch.core.presentation.util

/**
 * @auth：HGM
 * @date：2023-10-19 14:47
 * @desc：
 */
sealed class UiEvent {
      data class ShowSnackBar(val uiText: UiText) : UiEvent()
      data class Navigate(val route: String) : UiEvent()
      object NavigateUp : UiEvent()
}