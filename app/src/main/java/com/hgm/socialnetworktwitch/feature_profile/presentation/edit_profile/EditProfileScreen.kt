package com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.feature_post.util.CropActivityResultContract
import com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile.components.BannerEditSection
import com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile.components.Chip
import com.hgm.socialnetworktwitch.feature_profile.util.EditProfileError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditProfileScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      profilePictureSize: Dp = ProfilePictureSizeLarge,
      viewModel: EditProfileViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val profileState = viewModel.state.value

      val cropBannerLauncher = rememberLauncherForActivityResult(
            contract = CropActivityResultContract(5f, 2f)
      ) {
            viewModel.onEvent(EditProfileEvent.CropBannerPicture(it))
      }
      val cropProfileLauncher = rememberLauncherForActivityResult(
            contract = CropActivityResultContract(1f, 1f)
      ) {
            viewModel.onEvent(EditProfileEvent.CropProfilePicture(it))
      }
      val bannerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
      ) { uri ->
            uri?.let {
                  cropBannerLauncher.launch(it)
            }
      }
      val profileLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
      ) { uri ->
            uri?.let {
                  cropProfileLauncher.launch(it)
            }
      }

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.NavigateUp -> {
                              onNavigateUp()
                        }

                        is UiEvent.ShowSnackBar -> {
                              snackBarState.showSnackbar(
                                    message = event.uiText.asString(context)
                              )
                        }

                        else -> Unit
                  }
            }
      }



      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  onNavigateUp = onNavigateUp,
                  showBackIcon = true,
                  navAction = {
                        IconButton(onClick = {
                              viewModel.onEvent(EditProfileEvent.UpdateProfile)
                        }) {
                              Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = stringResource(id = R.string.save_changes),
                                    tint = MaterialTheme.colorScheme.onBackground
                              )
                        }
                  },
                  title = {
                        Text(
                              text = stringResource(id = R.string.edit_your_profile),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            )
            Column(
                  modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
            ) {
                  BannerEditSection(
                        context=context,
                        bannerImageUrl = viewModel.bannerPictureUri.value
                              ?: profileState.profile?.bannerUrl,
                        profileImageUrl = viewModel.profilePictureUri.value
                              ?: profileState.profile?.profilePictureUrl,
                        profilePictureSize = profilePictureSize,
                        onBannerClick = {
                              bannerLauncher.launch(
                                    PickVisualMediaRequest(
                                          ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                              )
                        },
                        onProfileImageClick = {
                              profileLauncher.launch(
                                    PickVisualMediaRequest(
                                          ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                              )
                        }
                  )
                  Column(
                        modifier = Modifier
                              .fillMaxWidth()
                              .padding(SpaceLarge)
                  ) {
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.usernameState.value.text,
                              hint = stringResource(id = R.string.username),
                              error = when (viewModel.usernameState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.username_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = Icons.Default.Person,
                              onValueChange = {
                                    viewModel.onEvent(EditProfileEvent.EnteredUsername(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.githubUrlState.value.text,
                              hint = stringResource(id = R.string.github_profile_url),
                              error = when (viewModel.githubUrlState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.github_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_icon_1),
                              onValueChange = {
                                    viewModel.onEvent(EditProfileEvent.EnteredGitHubUrl(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.instagramUrlState.value.text,
                              hint = stringResource(id = R.string.instagram_profile_url),
                              error = when (viewModel.instagramUrlState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.instagram_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram_glyph_1),
                              onValueChange = {
                                    viewModel.onEvent(EditProfileEvent.EnteredInstagramUrl(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.linkedInUrlState.value.text,
                              hint = stringResource(id = R.string.linked_in_profile_url),
                              error = when (viewModel.linkedInUrlState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.linkedin_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_icon_1),
                              onValueChange = {
                                    viewModel.onEvent(EditProfileEvent.EnteredLinkedInUrl(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.bioState.value.text,
                              hint = stringResource(id = R.string.your_bio),
                              error = when (viewModel.bioState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.bio_cant_be_empty)
                                    else -> ""
                              },
                              singleLine = false,
                              maxLines = 3,
                              leadingIcon = Icons.Default.Description,
                              onValueChange = {
                                    viewModel.onEvent(EditProfileEvent.EnteredBio(it))
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        Text(
                              text = stringResource(id = R.string.select_top_3_skills),
                              style = MaterialTheme.typography.displayMedium,
                              textAlign = TextAlign.Center,
                              modifier = Modifier.align(CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(SpaceLarge))

                        FlowRow(
                              modifier = Modifier.fillMaxWidth(),
                              mainAxisAlignment = MainAxisAlignment.Center,
                              mainAxisSpacing = SpaceMedium,
                              crossAxisSpacing = SpaceMedium
                        ) {
                              viewModel.skills.value.skills.forEach { skill ->
                                    Chip(
                                          text = skill.name,
                                          selected = viewModel.skills.value.selectedSkills.any { it.name == skill.name },
                                          onChipClick = {
                                                viewModel.onEvent(EditProfileEvent.SetSkillSelected(skill))
                                          }
                                    )
                              }
                        }
                  }
            }
      }
}

