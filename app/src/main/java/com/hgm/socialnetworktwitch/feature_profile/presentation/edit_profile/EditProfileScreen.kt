package com.hgm.socialnetworktwitch.feature_profile.presentation.edit_profile

/**
 * @auth：HGM
 * @date：2023-10-12 10:39
 * @desc：编辑个人信息界面
 */
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.feature_post.util.PostError
import com.hgm.socialnetworktwitch.feature_profile.util.EditProfileError
import kotlin.random.Random

@Composable
fun EditProfileScreen(
      navController: NavController,
      viewModel: EditProfileViewModel = hiltViewModel(),
      profilePictureSize: Dp = ProfilePictureSizeLarge
) {
      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  navController = navController,
                  showBackIcon = true,
                  navAction = {
                        IconButton(onClick = {}) {
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
                        bannerImage = painterResource(id = R.drawable.channel),
                        profileImage = painterResource(id = R.drawable.germen),
                        profilePictureSize = profilePictureSize
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
                                    viewModel.setUsernameState(
                                          StandardTextFieldState(text = it)
                                    )
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.githubTextFieldState.value.text,
                              hint = stringResource(id = R.string.github_profile_url),
                              error = when (viewModel.githubTextFieldState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.github_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_icon_1),
                              onValueChange = {
                                    viewModel.setGithubTextFieldState(
                                          StandardTextFieldState(text = it)
                                    )
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.instagramTextFieldState.value.text,
                              hint = stringResource(id = R.string.instagram_profile_url),
                              error =when (viewModel.instagramTextFieldState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.instagram_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram_glyph_1),
                              onValueChange = {
                                    viewModel.setInstagramTextFieldState(
                                          StandardTextFieldState(text = it)
                                    )
                              }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        StandardTextField(
                              modifier = Modifier
                                    .fillMaxWidth(),
                              text = viewModel.linkedInTextFieldState.value.text,
                              hint = stringResource(id = R.string.linked_in_profile_url),
                              error = when (viewModel.linkedInTextFieldState.value.error) {
                                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.linkedin_url_cant_be_empty)
                                    else -> ""
                              },
                              leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_icon_1),
                              onValueChange = {
                                    viewModel.setLinkedInTextFieldState(
                                          StandardTextFieldState(text = it)
                                    )
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
                                    viewModel.setBioState(
                                          StandardTextFieldState(text = it)
                                    )
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
                              listOf(
                                    "Kotlin",
                                    "JavaScript",
                                    "Assembly",
                                    "C++",
                                    "C",
                                    "C#",
                                    "Dart",
                                    "TypeScript",
                                    "Python",
                              ).forEach {
                                    Chip(
                                          text = it,
                                          selected = Random.nextInt(2) == 0,
                                    ){
                                      //TODO
                                    }
                              }
                        }
                  }
            }
      }
}

