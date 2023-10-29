package com.hgm.socialnetworktwitch.core.presentation

/**
 * @auth：HGM
 * @date：2023-10-29 17:11
 * @desc：记录我们自定义分页中的状态
 */
data class PagingState<T>(
      val items: List<T> = emptyList(),
      val isLoading:Boolean=false,
      val endReached:Boolean=false,//是否到底（没有数据了）
)
