package com.hgm.socialnetworktwitch.feature_auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.socialnetworktwitch.core.presentation.route.Screen
import com.hgm.socialnetworktwitch.core.presentation.util.UiEvent
import com.hgm.socialnetworktwitch.core.util.Resource
import com.hgm.socialnetworktwitch.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
      private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

      private val _eventFlow= MutableSharedFlow<UiEvent>()
      val eventFlow=_eventFlow.asSharedFlow()

      init {
            //检验token
            viewModelScope.launch {
                   when (authenticateUseCase()) {
                        is Resource.Error -> {
                              _eventFlow.emit(
                                    UiEvent.Navigate(Screen.LoginScreen.route)
                              )
                        }
                        is Resource.Success -> {
                              _eventFlow.emit(
                                    UiEvent.Navigate(Screen.MainFeedScreen.route)
                              )
                        }
                  }
            }
      }
}