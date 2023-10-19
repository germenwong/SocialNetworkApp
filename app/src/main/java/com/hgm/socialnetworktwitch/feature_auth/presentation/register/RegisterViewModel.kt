package com.hgm.socialnetworktwitch.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.PasswordTextFieldState
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.core.util.UiText
import com.hgm.socialnetworktwitch.feature_auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
      private val registerUseCase: RegisterUseCase
) : ViewModel() {
      //邮箱
      private val _emailState = mutableStateOf(StandardTextFieldState())
      val emailState: State<StandardTextFieldState> = _emailState

      //用户名
      private val _usernameState = mutableStateOf(StandardTextFieldState())
      val usernameState: State<StandardTextFieldState> = _usernameState

      //密码
      private val _passwordState = mutableStateOf(PasswordTextFieldState())
      val passwordState: State<PasswordTextFieldState> = _passwordState

      //注册状态
      private val _state = mutableStateOf(RegisterState())
      val state: State<RegisterState> = _state

      //事件流
      private val _eventFlow = MutableSharedFlow<UiEvent>()
      val eventFlow = _eventFlow.asSharedFlow()





      /** 处理UI发送过来的事件 */
      fun onEvent(event: RegisterEvent) {
            when (event) {
                  is RegisterEvent.EnteredEmail -> {
                        _emailState.value = _emailState.value.copy(
                              text = event.value
                        )
                  }

                  is RegisterEvent.EnteredUsername -> {
                        _usernameState.value = _usernameState.value.copy(
                              text = event.value
                        )
                  }

                  is RegisterEvent.EnteredPassword -> {
                        _passwordState.value = _passwordState.value.copy(
                              text = event.value
                        )
                  }

                  is RegisterEvent.TogglePasswordVisibility -> {
                        _passwordState.value = _passwordState.value.copy(
                              isPasswordVisible = !_passwordState.value.isPasswordVisible
                        )
                  }

                  is RegisterEvent.Register -> {
                        register()
                  }
            }
      }

      /** 注册 */
      private fun register() {
            viewModelScope.launch {
                  //每次注册之前都要把上次的错误清除掉
                  _emailState.value = _emailState.value.copy(error = null)
                  _usernameState.value = _usernameState.value.copy(error = null)
                  _passwordState.value = _passwordState.value.copy(error = null)
                  _state.value = RegisterState(isLoading = true)

                  //注册
                  val registerError = registerUseCase(
                        email = emailState.value.text,
                        username = usernameState.value.text,
                        password = passwordState.value.text
                  )

                  //处理字段的错误
                  if (registerError.emailError != null) {
                        _emailState.value = _emailState.value.copy(
                              error = registerError.emailError
                        )
                  }
                  if (registerError.usernameError != null) {
                        _usernameState.value = _usernameState.value.copy(
                              error = registerError.usernameError
                        )
                  }
                  if (registerError.passwordError != null) {
                        _passwordState.value = _passwordState.value.copy(
                              error = registerError.passwordError
                        )
                  }

                  //处理网络结果
                  when (registerError.result) {
                        is Resource.Success -> {
                              _eventFlow.emit(
                                    UiEvent.SnackBarEvent(UiText.StringResource(R.string.register_successful))
                              )
                              _state.value = RegisterState(isLoading = false)
                              _emailState.value = StandardTextFieldState()
                              _usernameState.value = StandardTextFieldState()
                              _passwordState.value = PasswordTextFieldState()
                        }

                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.SnackBarEvent(
                                          registerError.result.uiText
                                                ?: UiText.StringResource(R.string.error_unknown)
                                    )
                              )
                              _state.value = RegisterState(isLoading = false)
                        }

                        null -> {
                              _state.value = RegisterState(isLoading = false)
                        }
                  }
            }
      }


      /** 传给UI的事件 */
      sealed class UiEvent {
            data class SnackBarEvent(val uiText: UiText) : UiEvent()
      }
}