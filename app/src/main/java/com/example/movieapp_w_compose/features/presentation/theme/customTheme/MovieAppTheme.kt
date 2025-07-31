package com.example.movieapp_w_compose.features.presentation.theme.customTheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalAppColors = staticCompositionLocalOf { LightAppColor }
val LocalAppTypograph = staticCompositionLocalOf { LightTypography }


object MovieAppTheme {
    val colors: MovieAppColors
        @Composable get() = LocalAppColors.current

    val typograph: MovieAppTypograph
        @Composable get() = LocalAppTypograph.current
}

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkAppColor else LightAppColor
    val typograph = LightTypography
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypograph provides typograph
    ) { content() }

}