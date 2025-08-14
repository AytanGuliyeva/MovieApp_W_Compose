package com.example.movieapp_w_compose.retrofit.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class MovieResponse(
    @SerializedName("results")
    val results: List<Movie>
)

@Immutable
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
)

@Immutable
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

@Immutable
data class Genre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
@Immutable
data class ReviewResponse(
    @SerializedName("results")
    val results: List<Review>
)

@Immutable
data class Review(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,

)

@Immutable
data class GenreEntity(
    val id: Int,
    val name: String
)

fun Genre.toEntity()=GenreEntity(
    id = id?:0,
    name = name.orEmpty()
)