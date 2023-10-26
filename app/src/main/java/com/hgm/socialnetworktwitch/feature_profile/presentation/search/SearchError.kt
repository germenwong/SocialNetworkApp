package com.hgm.socialnetworktwitch.feature_profile.presentation.search

import com.hgm.socialnetworktwitch.core.domain.model.Error
import com.hgm.socialnetworktwitch.core.presentation.util.UiText


data class SearchError(
      val message: UiText
): Error()