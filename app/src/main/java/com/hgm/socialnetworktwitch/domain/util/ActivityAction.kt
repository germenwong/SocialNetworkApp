package com.hgm.socialnetworktwitch.domain.util

/**
 * @auth：HGM
 * @date：2023-10-11 14:25
 * @desc：用户的活动类型
 */
sealed class ActivityAction {
      // 点赞帖子
      object LikedPost : ActivityAction()

      // 评论帖子
      object CommentOnPost : ActivityAction()

      // 关注你
      object FollowedYou : ActivityAction()
}
