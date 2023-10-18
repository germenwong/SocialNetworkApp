package com.hgm.socialnetworktwitch.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hgm.socialnetworktwitch.R

val quicksand = FontFamily(
      Font(R.font.quicksand_bold, FontWeight.Bold),
      Font(R.font.quicksand_light, FontWeight.Light),
      Font(R.font.quicksand_medium, FontWeight.Medium),
      Font(R.font.quicksand_regular, FontWeight.Normal),
      Font(R.font.quicksand_semibold, FontWeight.SemiBold),
)


val Typography = Typography(
      //body1
      bodyLarge = TextStyle(
            fontFamily = quicksand,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            color = TextWhite
      ),
      //body2
      bodyMedium = TextStyle(
            fontFamily = quicksand,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = TextWhite
      ),
      //h1
      displayLarge = TextStyle(
            fontFamily = quicksand,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = TextWhite
      ),
      //h2
      displayMedium = TextStyle(
            fontFamily = quicksand,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            color = TextWhite
      ),

)