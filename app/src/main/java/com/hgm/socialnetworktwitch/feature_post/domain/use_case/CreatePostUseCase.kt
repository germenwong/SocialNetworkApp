package com.hgm.socialnetworktwitch.feature_post.domain.use_case

import android.net.Uri
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.repository.PostRepository

/**
 * @auth：HGM
 * @date：2023-10-19 18:05
 * @desc：创建帖子用例
 */
class CreatePostUseCase(
      private val repository: PostRepository
) {

      suspend operator fun invoke(description: String, imageUri: Uri?): SimpleResource {
            if(imageUri == null) {
                  return Resource.Error(
                        uiText = UiText.StringResource(R.string.error_no_image_picked)
                  )
            }
            if(description.isBlank()) {
                  return Resource.Error(
                        uiText = UiText.StringResource(R.string.error_description_blank)
                  )
            }
            return repository.createPost(description, imageUri)
      }
}