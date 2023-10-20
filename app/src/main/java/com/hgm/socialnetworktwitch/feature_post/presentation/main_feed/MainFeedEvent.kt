package com.hgm.socialnetworktwitch.feature_post.presentation.main_feed

/**
 * @auth：HGM
 * @date：2023-10-19 17:10
 * @desc：
 */
sealed class MainFeedEvent {
      object LoadMorePosts : MainFeedEvent()
}
