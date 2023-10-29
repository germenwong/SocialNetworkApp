package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.domain.use_case.UpdateFollowUseCase


data class ProfileUseCases(
      val getProfileUseCase: GetProfileUseCase,
      val getSkillsUseCase: GetSkillsUseCase,
      val updateProfileUseCase: UpdateProfileUseCase,
      val setSkillSelectedUseCase: SetSkillSelectedUseCase,
      val getPostsForProfileUseCase: GetPostsForProfileUseCase,
      val searchUserUseCase: SearchUserUseCase,
      val updateFollowUseCase: UpdateFollowUseCase
)