package com.example.movieapp_w_compose.features.presentation.movieDetail.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme


@Composable
fun AboutTab( overview: String) {
    Text(
        text = overview,
        style = MovieAppTheme.typograph.footnoteRegular,
        modifier = Modifier.padding(16.dp)
    )
}