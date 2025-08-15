package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.VideoEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class VideoDTO(
    @SerializedName("key")
    val key: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("type")
    val type: String?
)

fun VideoDTO.toVideoEntity() = VideoEntity(
    key = key.orEmpty(),
    site = site.orEmpty(),
    type = type.orEmpty()
)