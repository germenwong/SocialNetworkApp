package com.hgm.socialnetworktwitch.core.domain.states

import com.hgm.socialnetworktwitch.core.domain.model.Error

/**
 * @auth：HGM
 * @date：2023-10-12 11:37
 * @desc：
 */
data class StandardTextFieldState(
      val text: String = "",
      val error: Error?=null
)
