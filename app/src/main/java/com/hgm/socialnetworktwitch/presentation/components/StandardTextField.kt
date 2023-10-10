package com.hgm.socialnetworktwitch.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * @auth：HGM
 * @date：2023-09-22 15:41
 * @desc：
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
      text: String,
      hint: String,
      error: String = "",
      keyboardType: KeyboardType = KeyboardType.Text,
      showPasswordToggle: Boolean = false,
      onPasswordToggleClick: (Boolean) -> Unit = {},
      onValueChange: (String) -> Unit,
      modifier: Modifier = Modifier
) {
      //是否显示密码开关
      var passwordToggle by remember {
            mutableStateOf(keyboardType == KeyboardType.Password)
      }

      Column(
            modifier = modifier.fillMaxWidth()
      ) {
            TextField(
                  value = text,
                  onValueChange = onValueChange,
                  placeholder = {
                        Text(text = hint, style = MaterialTheme.typography.bodyLarge)
                  },
                  singleLine = true,
                  isError = error != "",
                  keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType
                  ),
                  visualTransformation = if (showPasswordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                  trailingIcon = {
                        if (passwordToggle) {
                              IconButton(
                                    onClick = { onPasswordToggleClick(!showPasswordToggle) },
                              ) {
                                    Icon(
                                          imageVector = if (showPasswordToggle) {
                                                Icons.Filled.Visibility
                                          } else {
                                                Icons.Filled.VisibilityOff
                                          },
                                          contentDescription = null
                                    )
                              }
                        }
                  },
                  modifier = modifier.fillMaxWidth()
            )

            if (error.isNotEmpty()) {
                  Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                  )
            }
      }


}
