package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository


class GetSkillsUseCase(
      private val repository: ProfileRepository
) {
      suspend operator fun invoke():Resource<List<Skill>>{
            return repository.getSkills()
      }
}