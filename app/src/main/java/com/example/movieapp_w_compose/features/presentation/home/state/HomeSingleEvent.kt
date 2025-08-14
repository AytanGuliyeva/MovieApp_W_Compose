package com.example.movieapp_w_compose.features.presentation.home.state

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class HomeSingleEvent:UiSingleEvent {
    data class OpenDetailMovieScreen(val movieId: Int) : HomeSingleEvent()
}