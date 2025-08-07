package com.example.movieapp_w_compose.features.presentation.search

import com.example.movieapp_w_compose.retrofit.model.Movie

data class SearchState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)