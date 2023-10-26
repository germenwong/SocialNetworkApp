package com.hgm.socialnetworktwitch.feature_profile.domain.use_case

import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.feature_profile.domain.model.Skill
import com.hgm.socialnetworktwitch.feature_profile.util.ProfileConstants


class SetSkillSelectedUseCase {

      operator fun invoke(
            selectedSkills: List<Skill>,
            skillToToggle: Skill
      ): Resource<List<Skill>> {
            val skillInList = selectedSkills.find { it.name == skillToToggle.name }
            if(skillInList != null) {
                  return Resource.Success(selectedSkills - skillInList)
            }
            return if(selectedSkills.size >= ProfileConstants.MAX_SELECTED_CHIP_COUNT) {
                  Resource.Error(uiText = UiText.StringResource(R.string.error_max_selected_is_3))
            } else {
                  Resource.Success(selectedSkills + skillToToggle)
            }
      }
}