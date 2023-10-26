package com.hgm.socialnetworktwitch.feature_post.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.core.domain.model.Post
import kotlinx.coroutines.flow.Flow


interface PostRepository {

       fun getPostsForFollows(): Flow<PagingData<Post>>

      suspend fun createPost(description: String,imageUri: Uri): SimpleResource
}