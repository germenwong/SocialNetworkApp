package com.hgm.socialnetworktwitch.core.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import com.hgm.socialnetworktwitch.R
import com.hgm.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SendTextField(
      state: StandardTextFieldState,
      onValueChange: (String) -> Unit,
      onSend: () -> Unit,
      hint: String = "",
      canSendMessage: Boolean = true,
      isLoading: Boolean = false,
      focusRequester: FocusRequester = FocusRequester()
) {
      Row(
            modifier = Modifier
                  .background(MaterialTheme.colorScheme.surface)
                  .fillMaxWidth().height(100.dp)
                  .padding(SpaceLarge),
            verticalAlignment = Alignment.CenterVertically
      ) {
            StandardTextField(
                  hint = hint,
                  text = state.text,
                  modifier = Modifier.weight(1f),
                  onValueChange = onValueChange,
                  focusRequester = focusRequester,
                  backgroundColor = MaterialTheme.colorScheme.background
            )
            if (isLoading) {
                  CircularProgressIndicator(
                        modifier = Modifier
                              .padding(horizontal = 12.dp)
                              .size(24.dp),
                        strokeWidth = 2.dp
                  )
            } else {
                  IconButton(
                        onClick = onSend,
                        enabled = state.error == null || !canSendMessage
                  ) {
                        Icon(
                              imageVector = Icons.Default.Send,
                              tint = if (state.error == null && canSendMessage) {
                                    MaterialTheme.colorScheme.primary
                              } else MaterialTheme.colorScheme.background,
                              contentDescription = stringResource(id = R.string.send_comment)
                        )
                  }
            }
      }
}