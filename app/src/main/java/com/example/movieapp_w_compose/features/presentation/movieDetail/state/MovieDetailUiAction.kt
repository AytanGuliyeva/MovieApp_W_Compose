package com.example.movieapp_w_compose.features.presentation.movieDetail.state

import com.example.movieapp_w_compose.state.UiAction

sealed class MovieDetailUiAction:UiAction {
    data class Load(val movieId: Int) : MovieDetailUiAction()
    data object BackToHomeClick : MovieDetailUiAction()
    data class TabSelected(val index: Int) : MovieDetailUiAction()
    data object PlayToggle : MovieDetailUiAction()
}