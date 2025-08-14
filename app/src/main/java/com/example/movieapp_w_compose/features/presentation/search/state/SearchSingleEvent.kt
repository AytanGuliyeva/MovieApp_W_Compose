package com.example.movieapp_w_compose.features.presentation.search.state

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class SearchSingleEvent:UiSingleEvent {
    data class OpenDetailMovieScreen(val movieId: Int) : SearchSingleEvent()
}