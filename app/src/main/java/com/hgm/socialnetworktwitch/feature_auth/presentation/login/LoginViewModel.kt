package com.hgm.socialnetworktwitch.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.PasswordTextFieldState
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.presentation.util.UiText
import com.hgm.socialnetworktwitch.feature_auth.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
      private val loginUseCase: LoginUseCase
) : ViewModel() {
      //邮箱
      private val _emailState = mutableStateOf(StandardTextFieldState())
      val emailState: State<StandardTextFieldState> = _emailState

      //密码
      private val _passwordState = mutableStateOf(PasswordTextFieldState())
      val passwordState: State<PasswordTextFieldState> = _passwordState

      //注册状态
      private val _state = mutableStateOf(false)
      val state: State<Boolean> = _state

      //事件流
      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()


      fun onEvent(event: LoginEvent) {
            when (event) {
                  is LoginEvent.EnteredEmail -> {
                        _emailState.value = _emailState.value.copy(
                              text = event.value
                        )
                  }

                  is LoginEvent.EnteredPassword -> {
                        _passwordState.value = _passwordState.value.copy(
                              text = event.value
                        )
                  }

                  is LoginEvent.TogglePasswordVisibility -> {
                        _passwordState.value = _passwordState.value.copy(
                              isPasswordVisible = !_passwordState.value.isPasswordVisible
                        )
                  }

                  is LoginEvent.Login -> {
                        login()
                  }
            }
      }

      private fun login() {
            viewModelScope.launch {
                  _emailState.value = _emailState.value.copy(error = null)
                  _passwordState.value = _passwordState.value.copy(error = null)
                  _state.value = true

                  val loginError = loginUseCase(
                        email = emailState.value.text,
                        password = passwordState.value.text
                  )

                  if (loginError.emailError != null) {
                        _emailState.value = _emailState.value.copy(
                              error = loginError.emailError
                        )
                  }

                  if (loginError.passwordError != null) {
                        _passwordState.value = _passwordState.value.copy(
                              error = loginError.passwordError
                        )
                  }


                  when (loginError.result) {
                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.SnackBarEvent(
                                          loginError.result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                              _state.value = false
                        }

                        is Resource.Success -> {
                              _eventFlow.emit(
                                    UiEvent.Navigate(Screen.MainFeedScreen.route)
                              )
                              _state.value = false
                              _emailState.value = StandardTextFieldState()
                              _passwordState.value = PasswordTextFieldState()
                        }

                        null -> {
                              _state.value = false
                        }
                  }
            }
      }


}