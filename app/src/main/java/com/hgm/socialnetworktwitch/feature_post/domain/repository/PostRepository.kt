package com.hgm.socialnetworktwitch.feature_post.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.hgm.socialnetworktwitch.feature_post.domain.model.Comment
import com.hgm.socialnetworktwitch.core.util.SimpleResource
import com.hgm.socialnetworktwitch.core.domain.model.Post
import com.hgm.socialnetworktwitch.core.domain.model.UserItem
import com.hgm.socialnetworktwitch.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface PostRepository {

       fun getPostsForFollows(): Flow<PagingData<Post>>

      suspend fun createPost(description: String,imageUri: Uri): SimpleResource

      suspend fun getPostDetail(postId: String): Resource<Post>

      suspend fun addComment(postId: String,comment:String): SimpleResource

      suspend fun getCommentForPost(postId: String): Resource<List<Comment>>

      suspend fun likeParent(parentId: String, parentType: Int): SimpleResource

      suspend fun unlikeParent(parentId: String, parentType: Int): SimpleResource

      suspend fun getLikesForParent(parentId: String):Resource<List<UserItem>>
}