package com.hgm.socialnetworktwitch.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hgm.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall

/**
 * @auth：HGM
 * @date：2023-10-11 17:06
 * @desc：
 */
@Composable
fun ProfileNumber(
      modifier: Modifier = Modifier,
      number:Int,
      text:String
) {
      Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
      ) {
            Text(
                  text=number.toString(),
                  style = MaterialTheme.typography.displayLarge,
                  textAlign = TextAlign.Center,
                  fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                  text=text,
                  style = MaterialTheme.typography.bodyMedium,
                  textAlign = TextAlign.Center
            )
      }
}