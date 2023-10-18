package com.hgm.socialnetworktwitch.feature_post.presentation.create_post

/**
 * @auth：HGM
 * @date：2023-10-10 18:20
 * @desc：
 */
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.feature_post.util.PostError

@Composable
fun CreatePostScreen(
      navController: NavController,
      viewModel: CreatePostViewModel = hiltViewModel()
) {
      Column(
            modifier = Modifier.fillMaxSize()
      ) {
            StandardTopBar(
                  navController = navController,
                  showBackIcon = true,
                  title = {
                        Text(
                              text = stringResource(id = R.string.create_a_new_post),
                              fontWeight = FontWeight.Bold,
                              color = MaterialTheme.colorScheme.onBackground
                        )
                  }
            )
            Column(
                  modifier = Modifier
                        .fillMaxSize()
                        .padding(SpaceLarge)
            ) {
                  Box(
                        modifier = Modifier
                              .aspectRatio(16f / 9f)
                              .fillMaxWidth()
                              .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = MaterialTheme.shapes.medium
                              )
                              .clickable {

                              },
                        contentAlignment = Alignment.Center
                  ) {
                        Icon(
                              imageVector = Icons.Default.Add,
                              contentDescription = stringResource(id = R.string.choose_image),
                              tint = MaterialTheme.colorScheme.onBackground
                        )
                  }
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        modifier = Modifier
                              .fillMaxWidth(),
                        text = viewModel.descriptionState.value.text,
                        hint = stringResource(id = R.string.description),
                        error = when (viewModel.descriptionState.value.error) {
                             is PostError.FieldEmpty -> stringResource(id = R.string.description_cant_be_empty)
                              else -> ""
                        },
                        singleLine = false,
                        maxLines = 5,
                        onValueChange = {
                              viewModel.setDescriptionState(
                                    StandardTextFieldState(text = it)
                              )
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceLarge))
                  Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.End)
                  ) {
                        Text(
                              text = stringResource(id = R.string.post),
                              color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                  }
            }
      }
}
