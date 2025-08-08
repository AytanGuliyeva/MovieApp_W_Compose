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
    @SerializedName("isSaved")
    val isSaved: Boolean = false
)

@Immutable
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)

@Immutable
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
@Immutable
data class ReviewResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Review>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

@Immutable
data class Review(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)