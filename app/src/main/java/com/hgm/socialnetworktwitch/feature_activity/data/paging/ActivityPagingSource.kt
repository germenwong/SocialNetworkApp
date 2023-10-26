package com.hgm.socialnetworktwitch.feature_activity.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_activity.data.remote.ActivityApi
import com.hgm.socialnetworktwitch.feature_activity.domain.model.Activity
import retrofit2.HttpException
import java.io.IOException

/**
 * @auth：HGM
 * @date：2023-10-26 15:16
 * @desc：
 */
class ActivityPagingSource(
      private val activityApi: ActivityApi
) : PagingSource<Int, Activity>() {

      private var currentPage = 0

      override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Activity> {
            return try {
                  val nextPage = params.key ?: currentPage
                  val activities =
                        activityApi.getActivities(nextPage, Constants.PAGE_DEFAULT_SIZE)

                  LoadResult.Page(
                        data = activities.map { it.toActivity() },
                        prevKey = if (nextPage == 0) null else nextPage - 1,
                        nextKey = if (activities.isEmpty()) null else currentPage + 1
                  ).also { currentPage++ }
            } catch (e: HttpException) {
                  LoadResult.Error(e)
            } catch (e: IOException) {
                  LoadResult.Error(e)
            }
      }

      override fun getRefreshKey(state: PagingState<Int, Activity>): Int? {
            return state.anchorPosition
      }
}