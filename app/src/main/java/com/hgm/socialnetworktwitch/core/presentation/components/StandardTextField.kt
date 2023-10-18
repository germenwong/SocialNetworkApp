package com.hgm.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.IconSizeSmall

/**
 * @auth：HGM
 * @date：2023-09-22 15:41
 * @desc：
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
      text: String="",
      hint: String="",
      error: String = "",
      maxLines:Int=1,
      singleLine:Boolean=true,
      leadingIcon: ImageVector? = null,
      keyboardType: KeyboardType = KeyboardType.Text,
      isShowPasswordToggle: Boolean = keyboardType == KeyboardType.Password,
      isShowPassword: Boolean = true,
      onPasswordToggleClick: (Boolean) -> Unit = {},
      onValueChange: (String) -> Unit,
      modifier: Modifier = Modifier
) {

      Column(
            modifier = modifier.fillMaxWidth()
      ) {
            TextField(
                  value = text,
                  onValueChange = onValueChange,
                  placeholder = {
                        Text(text = hint, style = MaterialTheme.typography.bodyLarge)
                  },
                  maxLines = maxLines,
                  singleLine = singleLine,
                  isError = error != "",
                  keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType
                  ),
                  visualTransformation = if (!isShowPassword) PasswordVisualTransformation() else VisualTransformation.None,
                  leadingIcon = if (leadingIcon != null) {
                        val icon: @Composable () -> Unit = {
                              Icon(
                                    imageVector = leadingIcon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground,
                                    modifier = Modifier.size(IconSizeSmall)
                              )
                        }
                        icon
                  } else null,
                  trailingIcon = {
                        if (isShowPasswordToggle) {
                              IconButton(
                                    onClick = { onPasswordToggleClick(!isShowPassword) },
                              ) {
                                    Icon(
                                          imageVector = if (isShowPassword) {
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

