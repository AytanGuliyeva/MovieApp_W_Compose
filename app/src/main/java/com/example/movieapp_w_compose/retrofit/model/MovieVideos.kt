package com.example.movieapp_w_compose.retrofit.model


data class VideoResponse(
    val id: String,
    val results: List<Video>
)

data class Video(
    val id: String,
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String
)