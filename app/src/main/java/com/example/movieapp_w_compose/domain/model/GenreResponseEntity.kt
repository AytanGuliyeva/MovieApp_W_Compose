package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable


@Immutable
data class GenreResponseEntity(
    val genres: List<GenreEntity>
)
