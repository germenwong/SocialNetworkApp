package com.hgm.socialnetworktwitch.feature_auth.presentation.login

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.hgm.socialnetworktwitch.core.presentation.components.StandardTextField
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.RoundedCornerMedium
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.Constants
import com.hgm.socialnetworktwitch.feature_auth.util.AuthError
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
      navController: NavController,
      snackBarState: SnackbarHostState,
      viewModel: LoginViewModel = hiltViewModel()
) {
      val context = LocalContext.current
      val isLoading = viewModel.state.value
      val scope = rememberCoroutineScope()
      val emailState = viewModel.emailState.value
      val passwordState = viewModel.passwordState.value

      LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                  when (event) {
                        is UiEvent.Navigate -> {
                              navController.navigate(event.route)
                        }

                        is UiEvent.SnackBarEvent -> {
                              scope.launch {
                                    snackBarState.showSnackbar(
                                          event.uiText.asString(context),
                                          duration = SnackbarDuration.Long
                                    )
                              }
                        }
                  }
            }
      }


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
                        text = stringResource(id = R.string.login),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displayLarge
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        text = emailState.text,
                        hint = stringResource(id = R.string.email_hint),
                        keyboardType = KeyboardType.Email,
                        error = when (emailState.error) {
                              AuthError.FieldEmpty -> stringResource(id = R.string.email_cant_be_empty)
                              AuthError.InvalidEmail -> stringResource(id = R.string.email_not_valid)
                              else -> ""
                        },
                        onValueChange = {
                              viewModel.onEvent(LoginEvent.EnteredEmail(it))
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  StandardTextField(
                        text = passwordState.text,
                        hint = stringResource(id = R.string.password_hint),
                        keyboardType = KeyboardType.Password,
                        error = when (passwordState.error) {
                              AuthError.FieldEmpty -> stringResource(id = R.string.password_cant_be_empty)
                              AuthError.FieldTooShort -> stringResource(
                                    id = R.string.password_too_short,
                                    Constants.MIN_PASSWORD_LENGTH
                              )

                              else -> ""
                        },
                        isShowPassword = passwordState.isPasswordVisible,
                        onValueChange = {
                              viewModel.onEvent(LoginEvent.EnteredPassword(it))
                        },
                        onPasswordToggleClick = {
                              viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                        }
                  )
                  Spacer(modifier = Modifier.height(SpaceMedium))
                  Button(
                        modifier = Modifier.align(Alignment.End),
                        shape = RoundedCornerShape(RoundedCornerMedium),
                        onClick = {
                              viewModel.onEvent(LoginEvent.Login)
                        }
                  ) {
                        Text(
                              text = stringResource(id = R.string.login),
                              color = MaterialTheme.colorScheme.onPrimary
                        )
                  }
                  if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                  }
            }

            Text(
                  text = buildAnnotatedString {
                        append(stringResource(id = R.string.dont_have_an_account_yet))
                        append(" ")
                        withStyle(
                              style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                              )
                        ) {
                              append(stringResource(id = R.string.sign_up))
                        }
                  },
                  style = MaterialTheme.typography.bodyLarge,
                  modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clickable {
                              navController.navigate(Screen.RegisterScreen.route)
                        }
            )
      }

}