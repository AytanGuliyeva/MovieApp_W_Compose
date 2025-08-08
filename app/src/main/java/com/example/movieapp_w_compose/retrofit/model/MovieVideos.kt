package com.example.movieapp_w_compose.retrofit.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class VideoResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("results")
    val results: List<Video>
)

@Immutable
data class Video(
    @SerializedName("id")
    val id: String,
    @SerializedName("iso_639_1")
    val iso_639_1: String,
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
)