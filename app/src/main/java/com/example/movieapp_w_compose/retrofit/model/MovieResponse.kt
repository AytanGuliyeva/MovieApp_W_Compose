package com.example.movieapp_w_compose.retrofit.model

data class MovieResponse(
    val results: List<Movie>
)


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val isSaved: Boolean = false
)


data class GenreResponse(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)
data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
)

data class Review(
    val author: String,
    val content: String,
    val created_at: String,
    val id: String,
    val url: String
)