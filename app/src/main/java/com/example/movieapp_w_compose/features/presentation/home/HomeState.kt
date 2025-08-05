package com.example.movieapp_w_compose.features.presentation.home

import com.example.movieapp_w_compose.retrofit.model.Movie

data class HomeState(
    val sliderImageUrls:List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val popularMovies : List<Movie> = emptyList()
)