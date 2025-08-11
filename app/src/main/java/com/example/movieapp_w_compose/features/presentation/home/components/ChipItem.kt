package com.example.movieapp_w_compose.features.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.retrofit.model.Genre

@Composable
fun ChipItem(genre: Genre, isSelected: Boolean, onClick: (Genre) -> Unit) {
    AssistChip(
        onClick = { onClick(genre) },
        label = { Text(text = genre.name) },
        colors = if (isSelected) {
            AssistChipDefaults.assistChipColors(
                containerColor = MovieAppTheme.colors.red
            )
        } else {
            AssistChipDefaults.assistChipColors(
                containerColor = MovieAppTheme.colors.menuColor,
                labelColor = MovieAppTheme.colors.white
            )
        },
        modifier = Modifier.padding(end = 4.dp)
    )
}
