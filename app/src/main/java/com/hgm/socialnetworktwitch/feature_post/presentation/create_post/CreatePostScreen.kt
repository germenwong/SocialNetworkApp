package com.hgm.socialnetworktwitch.feature_post.presentation.create_post


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTopBar
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.feature_post.util.CropActivityResultContract
import com.hgm.socialnetworktwitch.feature_post.util.PostError
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun CreatePostScreen(
      onNavigateUp: () -> Unit = {},
      onNavigate: (String) -> Unit = {},
      snackBarState: SnackbarHostState,
      viewModel: CreatePostViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val imageUri = viewModel.pickedImageUri.value
      //裁剪启动器
      val cropActivityLauncher = rememberLauncherForActivityResult(
            contract = CropActivityResultContract(16f, 9f)
      ) {
            viewModel.onEvent(CreatePostEvent.CropImage(it))
      }
      //图片启动器
      val photoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
      ) { uri ->
            uri?.let {
                  cropActivityLauncher.launch(it)
            }
      }

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.NavigateUp -> onNavigateUp()

                        is UiEvent.ShowSnackBar -> {
                              GlobalScope.launch {
                                    snackBarState.showSnackbar(
                                          message = event.uiText.asString(context)
                                    )
                              }
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
                              .clip(MaterialTheme.shapes.medium)
                              .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = MaterialTheme.shapes.medium
                              )
                              .clickable {
                                    photoPickerLauncher.launch(
                                          //由于默认获取的是视频与图片媒体，这里我们只需要图片
                                          //所以重写一下请求设置为只要访问图片
                                          PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                          )
                                    )
                              },
                        contentAlignment = Alignment.Center
                  ) {
                        Icon(
                              imageVector = Icons.Default.Add,
                              contentDescription = stringResource(id = R.string.choose_image),
                              tint = MaterialTheme.colorScheme.onBackground
                        )
                        imageUri?.let { uri ->
                              AsyncImage(
                                    model = uri,
                                    contentDescription = stringResource(id = R.string.post_image),
                                    //imageLoader = ,
                                    modifier = Modifier.matchParentSize()
                              )
                        }
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
                              viewModel.onEvent(
                                    CreatePostEvent.EnterDescription(it)
                              )
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceLarge))
                  Button(
                        onClick = {
                              viewModel.onEvent(CreatePostEvent.Post)
                        },
                        modifier = Modifier.align(Alignment.End)
                  ) {
                        Text(
                              text = stringResource(id = R.string.post),
                              color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        if (viewModel.isLoading.value) {
                              CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                        }
                  }
            }
      }
}
