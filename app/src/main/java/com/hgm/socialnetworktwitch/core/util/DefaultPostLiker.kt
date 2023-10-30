package com.hgm.socialnetworktwitch.core.util

import com.hgm.socialnetworktwitch.core.domain.model.Post

/**
 * @auth：HGM
 * @date：2023-10-30 14:40
 * @desc：
 */
class DefaultPostLiker : PostLiker {

      override suspend fun updateLikeState(
            posts: List<Post>,
            parentId: String,
            onRequest: suspend (isLiked:Boolean) -> SimpleResource,
            onStateUpdated: (List<Post>) -> Unit,
      ) {
            //记录点赞前的状态（是否点赞、点赞量）
            val currentPost = posts.find { it.id == parentId }
            val currentLiked = currentPost?.isLiked == true
            val currentLikeCount = currentPost?.likeCount ?: 0

            //发起点赞请求后立马更新状态，实现无延迟
            val newPosts = posts.map { post ->
                  if (post.id == parentId) {
                        post.copy(
                              isLiked = !post.isLiked,
                              likeCount = if (currentLiked) {
                                    post.likeCount - 1
                              } else post.likeCount + 1
                        )
                  } else post
            }
            onStateUpdated(newPosts)

            //发起点赞请求，处理返回结果
            when (onRequest(currentLiked)) {
                  is Resource.Success -> Unit
                  is Resource.Error -> {
                        //如果请求失败，把点赞状态改回点赞前的状态
                        val oldPosts =posts.map { post ->
                              if (post.id == parentId) {
                                    post.copy(
                                          isLiked = currentLiked,
                                          likeCount = currentLikeCount
                                    )
                              } else post
                        }
                        onStateUpdated(oldPosts)
                  }
            }
      }
}