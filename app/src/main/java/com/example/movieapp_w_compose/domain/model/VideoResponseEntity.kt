package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class VideoResponseEntity(
    val results: List<VideoEntity>
)

