package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class VideoEntity(
    val key: String,
    val site: String,
    val type: String
)