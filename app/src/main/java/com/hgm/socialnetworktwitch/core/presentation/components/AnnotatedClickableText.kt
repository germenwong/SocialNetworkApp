package com.hgm.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.hgm.socialnetworktwitch.core.presentation.route.Screen


@Composable
fun AnnotatedClickableText(
      username:String,
      fillerText:String,
      actionText:String,
      onUsernameClick:()->Unit={},
      onParentClick:()->Unit={}
) {
      val annotatedText = buildAnnotatedString {
            val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
            pushStringAnnotation(
                  tag = "username",
                  annotation = "username"
            )
            withStyle(boldStyle) {
                  append(username)
            }
            pop()
            append(" $fillerText ")

            pushStringAnnotation(
                  tag = "parent",
                  annotation = "parent"
            )
            withStyle(boldStyle) {
                  append(actionText)
            }
      }
      ClickableText(
            text = annotatedText,
            style = TextStyle(
                  color = MaterialTheme.colorScheme.onBackground
            ),
            onClick = { offset ->
                  annotatedText.getStringAnnotations(
                        tag = "username",// 在buildAnnotatedString中使用的标记
                        start = offset,
                        end = offset
                  ).firstOrNull()?.let { annotation ->
                        onUsernameClick()
                  }
                  annotatedText.getStringAnnotations(
                        tag = "parent",// 在buildAnnotatedString中使用的标记
                        start = offset,
                        end = offset
                  ).firstOrNull()?.let { annotation ->
                        onParentClick()
                  }
            }
      )
}