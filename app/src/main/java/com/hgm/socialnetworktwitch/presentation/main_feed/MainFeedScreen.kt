package com.hgm.socialnetworktwitch.presentation.main_feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.domain.model.Post
import com.hgm.socialnetworktwitch.presentation.components.PostView

/**
 * @auth：HGM
 * @date：2023-10-10 11:22
 * @desc：
 */
@Composable
fun MainFeedScreen(
      navController: NavController
) {
      PostView(
            post = Post(
                  username = "Germen Wong",
                  imageUrl = "",
                  profilePicture = "",
                  description = "ChatGPT Android 版目前可以在 Google 商店直接下载，这需要 Google 服务的支持。" +
                          "部分 Android 设备自带的有（a1231aaa搜索“谷歌”或者“Google”找到对应项开启）。",
                  likeCount = 14,
                  commentCount = 53
            )
      )

}