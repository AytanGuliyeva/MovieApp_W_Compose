package com.example.movieapp_w_compose.features.presentation.home

import com.example.movieapp_w_compose.state.UiAction

sealed class HomeUiAction : UiAction{
    data object Load : HomeUiAction()
    data object NowPlayingMovies: HomeUiAction( )

}