package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String
)