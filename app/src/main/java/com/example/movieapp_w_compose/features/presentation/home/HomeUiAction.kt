package com.example.movieapp_w_compose.features.presentation.home

import com.example.movieapp_w_compose.state.UiAction

sealed class HomeUiAction : UiAction {
    data object Load : HomeUiAction()
    data object NowPlayingMovies : HomeUiAction()
    data object MovieGenres : HomeUiAction()
    data class GenreSelected(val genreId: Int) : HomeUiAction()
    data object GetPopularMovies : HomeUiAction()
    data object TopRatedMovies : HomeUiAction()
}