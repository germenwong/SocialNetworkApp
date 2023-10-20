package com.hgm.socialnetworktwitch.core.util

import com.hgm.socialnetworktwitch.core.presentation.util.UiText

/**
 * @auth：HGM
 * @date：2023-10-18 18:08
 * @desc：全局的网络处理类
 */
//使用别名区分一下
typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T?, val uiText: UiText? = null) {
      class Success<T>(data: T?) : Resource<T>(data)
      class Error<T>(uiText: UiText, data: T? = null) : Resource<T>(data, uiText)
}
