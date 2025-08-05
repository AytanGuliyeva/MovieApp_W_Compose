package com.example.movieapp_w_compose.features.presentation.home

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp_w_compose.retrofit.model.Genre

@Composable
fun ChipItem(genre: Genre, onClick: (Genre) -> Unit) {
    AssistChip(
        onClick = { onClick(genre) },
        label = { Text(text = genre.name) },
        colors = AssistChipDefaults.assistChipColors()
    )
}