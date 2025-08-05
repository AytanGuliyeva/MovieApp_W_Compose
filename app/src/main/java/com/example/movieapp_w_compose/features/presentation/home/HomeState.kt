package com.example.movieapp_w_compose.features.presentation.home

import com.example.movieapp_w_compose.retrofit.model.Genre
import com.example.movieapp_w_compose.retrofit.model.Movie

data class HomeState(
    val sliderImageUrls:List<String> = emptyList(),
    val genreMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val selectedGenreId: Int? = null,
    val genres: List<Genre> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val popularMovies : List<Movie> = emptyList(),
    val topRatedMovies:List<Movie> =  emptyList()
)