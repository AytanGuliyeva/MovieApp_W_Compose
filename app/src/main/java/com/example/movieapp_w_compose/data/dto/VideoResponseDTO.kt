package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.VideoResponseEntity
import com.google.gson.annotations.SerializedName


@Immutable
data class VideoResponseDTO(
    @SerializedName("results")
    val results: List<VideoDTO?>
)


fun VideoResponseDTO.toVideoResponseEntity() = VideoResponseEntity(
    results = results.mapNotNull { dtoItem ->
        dtoItem?.toVideoEntity()
    }
)
