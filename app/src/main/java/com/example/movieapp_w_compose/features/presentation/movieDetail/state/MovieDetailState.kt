package com.example.movieapp_w_compose.features.presentation.movieDetail.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.domain.MovieEntity
import com.example.movieapp_w_compose.data.domain.ReviewEntity
import com.example.movieapp_w_compose.data.dto.MovieDTO
import com.example.movieapp_w_compose.data.dto.ReviewDTO

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


    val reviews: List<ReviewEntity> = emptyList(),
    val selectedTabIndex: Int = 0,

    val similarMovie : List<MovieEntity> = emptyList(),

    )