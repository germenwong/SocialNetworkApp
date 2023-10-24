package com.hgm.socialnetworktwitch.feature_profile.domain.use_case


data class ProfileUseCases(
      val getProfileUseCase: GetProfileUseCase,
      val getSkillsUseCase: GetSkillsUseCase,
      val updateProfileUseCase: UpdateProfileUseCase,
      val selectedUseCase: SetSkillSelectedUseCase
)