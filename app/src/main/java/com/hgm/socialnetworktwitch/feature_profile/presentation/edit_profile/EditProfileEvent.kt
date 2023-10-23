package com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile

import android.net.Uri


sealed class EditProfileEvent{
      data class EnteredUsername(val value: String): EditProfileEvent()
      data class EnteredGitHubUrl(val value: String): EditProfileEvent()
      data class EnteredInstagramUrl(val value: String): EditProfileEvent()
      data class EnteredLinkedInUrl(val value: String): EditProfileEvent()
      data class EnteredBio(val value: String): EditProfileEvent()


      data class CropProfilePicture(val uri: Uri?): EditProfileEvent()
      data class CropBannerPicture(val uri: Uri?): EditProfileEvent()

      //data class SetSkillSelected(val skill: Skill): EditProfileEvent()

      object UpdateProfile: EditProfileEvent()
}
