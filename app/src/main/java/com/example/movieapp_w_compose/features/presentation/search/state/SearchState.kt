package com.example.movieapp_w_compose.features.presentation.search.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.domain.MovieEntity
import com.example.movieapp_w_compose.data.dto.MovieDTO

@Immutable
data class SearchState(
    val query: String = "",
    val movie: List<MovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)