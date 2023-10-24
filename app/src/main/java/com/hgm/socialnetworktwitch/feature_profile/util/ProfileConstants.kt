package com.hgm.socialnetworktwitch.feature_profile.util

/**
 * @auth：HGM
 * @date：2023-10-23 16:23
 * @desc：
 */
object ProfileConstants {

      const val MAX_SELECTED_CHIP_COUNT = 3

      val GITHUB_PROFILE_REGEX = "(https://)?(www\\.)?github\\.com/[A-z0-9_-]+/?".toRegex()
      val INSTAGRAM_PROFILE_REGEX = "(https?://)?(www\\.)?instagram\\.com/[a-z_\\-A-Z0-9]*".toRegex()
      val LINKED_IN_PROFILE_REGEX = "http(s)?://([\\w]+\\.)?linkedin\\.com/in/[A-z0-9_-]+/?".toRegex()

      const val SEARCH_DELAY = 700L
}