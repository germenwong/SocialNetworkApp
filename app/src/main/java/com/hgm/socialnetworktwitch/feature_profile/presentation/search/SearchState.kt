package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import com.hgm.socialnetworktwitch.feature_profile.domain.model.UserItem

/**
 * @auth：HGM
 * @date：2023-10-21 15:45
 * @desc：
 */
data class SearchState(
      val userItems: List<UserItem> = emptyList(),
      val isLoading: Boolean = false
)