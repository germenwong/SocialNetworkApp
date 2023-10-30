package com.hgm.socialnetworktwitch.core.util

/**
 * @auth：HGM
 * @date：2023-10-30 11:51
 * @desc：
 */
interface Paginator<T> {

      suspend fun loadNextItems()
}