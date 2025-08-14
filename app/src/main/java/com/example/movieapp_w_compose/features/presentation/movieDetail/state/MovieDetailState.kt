package com.example.movieapp_w_compose.features.presentation.movieDetail.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.retrofit.model.Movie
import com.example.movieapp_w_compose.retrofit.model.Review

@Immutable
data class MovieDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,

    val movieId: Int? = null,
    val title: String = "",
    val year: String = "",
    val overview: String = "",

    val posterUrl: String = "",

    val trailerUrl: String? = null,
    val isTrailerVisible: Boolean = false,


    val reviews: List<Review> = emptyList(),
    val selectedTabIndex: Int = 0,

    val similarMovies : List<Movie> = emptyList(),

    )