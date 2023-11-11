package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository


class AddComment(
      private val repository: PostRepository
) {
      suspend operator fun invoke(postId: String, comment: String): SimpleResource {
            if (postId.isBlank()) {
                  return Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
            if (comment.isBlank()) {
                  return Resource.Error(UiText.StringResource(R.string.comment_is_empty))
            }
            return repository.addComment(postId, comment)
      }
}