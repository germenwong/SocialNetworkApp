package com.hgm.socialnetworktwitch.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.presentation.ui.theme.SpaceMedium

/**
 * @auth：HGM
 * @date：2023-09-22 14:46
 * @desc：
 */
@Composable
fun RegisterScreen(
      navController: NavController,
      viewModel: RegisterViewModel = hiltViewModel()
) {
      Box(
            modifier = Modifier
                  .fillMaxSize()
                  .padding(
                        top = SpaceMedium,
                        start = SpaceMedium,
                        end = SpaceMedium,
                        bottom = 40.dp
                  ),
      ) {
            Column(
                  modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                  verticalArrangement = Arrangement.Center
            ) {
                  Text(
                        text = stringResource(id = R.string.register),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displayLarge
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        text = viewModel.emailText.value,
                        hint = stringResource(id = R.string.email),
                        error = viewModel.emailError.value,
                        keyboardType = KeyboardType.Email,
                        onValueChange = {
                              viewModel.setEmailText(it)
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        text = viewModel.usernameText.value,
                        hint = stringResource(id = R.string.username),
                        error = viewModel.usernameError.value,
                        onValueChange = {
                              viewModel.setUsernameText(it)
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        text = viewModel.passwordText.value,
                        hint = stringResource(id = R.string.password),
                        keyboardType = KeyboardType.Password,
                        error = viewModel.passwordError.value,
                        isShowPassword = viewModel.showPassword.value,
                        onValueChange = {
                              viewModel.setPasswordText(it)
                        },
                        onPasswordToggleClick = {
                              viewModel.setShowPassword(it)
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  Button(
                        modifier = Modifier.align(Alignment.End),
                        shape = RoundedCornerShape(RoundedCornerMedium),
                        onClick = {
                              //TODO
                        }
                  ) {
                        Text(
                              text = stringResource(id = R.string.register),
                              color = MaterialTheme.colorScheme.onPrimary
                        )
                  }
            }

            Text(
                  text = buildAnnotatedString {
                        append(stringResource(id = R.string.already_have_an_account))
                        append(" ")
                        withStyle(
                              style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                              )
                        ) {
                              append(stringResource(id = R.string.sign_in))
                        }
                  },
                  style = MaterialTheme.typography.bodyLarge,
                  modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clickable {
                              navController.popBackStack()
                        }
            )
      }

}