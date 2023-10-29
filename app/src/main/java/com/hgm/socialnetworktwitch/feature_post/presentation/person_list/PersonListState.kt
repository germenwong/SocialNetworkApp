package com.hgm.socialnetworktwitch.feature_post.presentation.person_list

import com.hgm.socialnetworktwitch.core.domain.model.UserItem


data class PersonListState(
      val users: List<UserItem> = emptyList(),
      val isLoading: Boolean = false
)
