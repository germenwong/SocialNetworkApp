package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import com.hgm.socialnetworktwitch.core.domain.model.UserItem


data class SearchState(
      val userItems: List<UserItem> = emptyList(),
      val isLoading: Boolean = false
)