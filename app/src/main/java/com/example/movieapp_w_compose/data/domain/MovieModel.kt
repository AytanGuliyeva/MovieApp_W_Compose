package com.example.movieapp_w_compose.data.domain

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.dto.GenreDTO
import com.example.movieapp_w_compose.data.dto.MovieDTO
import com.example.movieapp_w_compose.data.dto.ReviewDTO
import com.google.gson.annotations.SerializedName

@Immutable
data class MovieResponseEntity(
    val results: List<MovieEntity>
)

@Immutable
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String
)

@Immutable
data class GenreResponseEntity(
    val genres: List<GenreEntity>
)

@Immutable
data class GenreEntity(
    val id: Int,
    val name: String
)

@Immutable
data class ReviewResponseEntity(
    val results: List<ReviewEntity>
)

@Immutable
data class ReviewEntity(
    val author: String,
    val content: String,
    val id: String
)