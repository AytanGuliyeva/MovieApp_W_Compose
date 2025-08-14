package com.example.movieapp_w_compose.retrofit.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class VideoResponse(
    @SerializedName("results")
    val results: List<Video>
)

@Immutable
data class Video(
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String
)