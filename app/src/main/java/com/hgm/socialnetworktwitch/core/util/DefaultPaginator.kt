package com.hgm.socialnetworktwitch.core.util

import com.hgm.socialnetworktwitch.core.presentation.util.UiText

/**
 * @auth：HGM
 * @date：2023-10-30 11:43
 * @desc：分页器
 */
class DefaultPaginator<T>(
      private val onLoading: (Boolean) -> Unit,
      private val onRequest: suspend (page:Int) -> Resource<List<T>>,
      private val onError: suspend (UiText) -> Unit,
      private val onSuccess: (List<T>) -> Unit
) : Paginator<T> {

      /** 当前页码 */
      private var currentPage = 0

      /** 加载下一页 */
      override suspend fun loadNextItems() {
            onLoading(true)

            when (val result = onRequest(currentPage)) {
                  is Resource.Success -> {
                        val items = result.data ?: emptyList()
                        currentPage++
                        onSuccess(items)
                        onLoading(false)
                  }

                  is Resource.Error -> {
                        onError(result.uiText ?: UiText.unknownError())
                        onLoading(false)
                  }
            }
      }
}