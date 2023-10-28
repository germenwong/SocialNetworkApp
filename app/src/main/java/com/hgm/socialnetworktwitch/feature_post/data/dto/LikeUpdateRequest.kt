package com.hgm.socialnetworktwitch.feature_post.data.dto


data class LikeUpdateRequest(
    val parentId: String,
    val parentType: Int
)
