package com.example.movieapp_w_compose.features.presentation.home.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.GenreEntity
import com.example.movieapp_w_compose.domain.model.MovieEntity

@Immutable
data class HomeState(
    val sliderImageUrls:List<String> = emptyList(),
    val genreMovie: List<MovieEntity> = emptyList(),
    val selectedGenreId: Int? = null,
    val genres: List<GenreEntity> = emptyList(),
    val popularMovie : List<MovieEntity> = emptyList(),
    val topRatedMovie:List<MovieEntity> =  emptyList()
)