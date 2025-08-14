package com.example.movieapp_w_compose.data.domain

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.dto.VideoDTO
import com.google.gson.annotations.SerializedName

@Immutable
data class VideoResponseEntity(
    val results: List<VideoEntity>
)

@Immutable
data class VideoEntity(
    val key: String,
    val site: String,
    val type: String
)