package com.hgm.socialnetworktwitch.feature_post.presentation.person_list


sealed class PersonListEvent{
      data class UpdateFollowState(val userId: String): PersonListEvent()
}
