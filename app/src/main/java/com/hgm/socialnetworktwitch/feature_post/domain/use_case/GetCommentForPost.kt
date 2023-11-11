package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository


class GetCommentForPost(
      private val repository: PostRepository
) {

      suspend operator fun invoke(postId: String): Resource<List<Comment>> {
            return repository.getCommentForPost(postId)
      }
}