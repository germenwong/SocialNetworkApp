package com.hgm.socialnetworktwitch.feature_post.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import kotlinx.coroutines.flow.Flow
import java.io.File


interface PostRepository {

       fun getPostsForFollows(): Flow<PagingData<Post>>

      suspend fun createPost(description: String,imageUri: Uri): SimpleResource
}