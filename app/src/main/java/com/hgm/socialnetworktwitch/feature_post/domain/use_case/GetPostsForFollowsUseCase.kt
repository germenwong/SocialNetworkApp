package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

/**
 * @auth：HGM
 * @date：2023-10-19 16:09
 * @desc：获取关注列表中的人的所有帖子
 */
class GetPostsForFollowsUseCase(
      private val repository: PostRepository
) {

       operator fun invoke(): Flow<PagingData<Post>> {
            return repository.getPostsForFollows()
      }
}