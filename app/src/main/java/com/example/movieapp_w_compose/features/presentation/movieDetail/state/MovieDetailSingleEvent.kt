package com.example.movieapp_w_compose.features.presentation.movieDetail.state

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class MovieDetailSingleEvent:UiSingleEvent {
    data object OpenHomeScreen : MovieDetailSingleEvent()
}