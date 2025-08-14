package com.example.movieapp_w_compose.features.presentation.search.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.retrofit.model.Movie

@Immutable
data class SearchState(
    val query: String = "",
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)