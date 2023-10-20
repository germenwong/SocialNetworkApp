package com.hgm.socialnetworktwitch.feature_post.domain.repository

import android.net.Uri
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import java.io.File


interface PostRepository {

      //TODO
      suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>>

      suspend fun createPost(description: String,imageUri: Uri): SimpleResource
}