package com.example.movieapp_w_compose.features.presentation.home.state

import com.example.movieapp_w_compose.state.UiAction

sealed class HomeUiAction : UiAction {
    data object Load : HomeUiAction()
    data class MovieClicked(val id: Int) : HomeUiAction()
    data class GenreSelected(val genreId: Int?) : HomeUiAction()
}

