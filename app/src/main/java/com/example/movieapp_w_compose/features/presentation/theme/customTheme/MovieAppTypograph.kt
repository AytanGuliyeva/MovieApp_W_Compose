package com.example.movieapp_w_compose.features.presentation.theme.customTheme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class MovieAppTypograph(
    val headlineMedium: TextStyle,
    val titleMedium: TextStyle,
    val subtitleMedium: TextStyle,
    val calloutMedium: TextStyle,
    val footnoteRegular: TextStyle,
)

val LightTypography = MovieAppTypograph(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    calloutMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    ),
    footnoteRegular = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Gray
    ),
    subtitleMedium = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    ),
    titleMedium = TextStyle(
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    )
)