package com.hgm.socialnetworktwitch.feature_post.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_post.data.remote.PostApi
import com.hgm.socialnetworktwitch.feature_post.domain.model.Post
import retrofit2.HttpException
import java.io.IOException

/**
 * @auth：HGM
 * @date：2023-10-19 16:38
 * @desc：远程中介器
 */
class PostPagingSource(
      private val api: PostApi
) : PagingSource<Int, Post>() {

      private var currentPage = 0

      override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
            return try {
                  val nextPage = params.key ?: currentPage
                  val posts =
                        api.getPostsForFollows(page = nextPage, pageSize = Constants.PAGE_SIZE_POST)
                  LoadResult.Page(
                        data = posts,
                        prevKey = if (nextPage == 0) null else nextPage - 1,
                        nextKey = if (posts.isEmpty()) null else currentPage + 1
                  ).also { currentPage++ }
            } catch (e: HttpException) {
                  LoadResult.Error(e)
            } catch (e: IOException) {
                  LoadResult.Error(e)
            }
      }

      override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
            return state.anchorPosition
      }


}