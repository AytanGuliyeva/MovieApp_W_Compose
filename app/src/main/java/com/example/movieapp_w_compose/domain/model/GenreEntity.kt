package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class GenreEntity(
    val id: Int,
    val name: String
)
