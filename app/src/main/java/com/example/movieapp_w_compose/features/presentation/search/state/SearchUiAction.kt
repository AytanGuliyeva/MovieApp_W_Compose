package com.example.movieapp_w_compose.features.presentation.search.state

import com.example.movieapp_w_compose.state.UiAction

sealed class SearchUiAction :UiAction{
    data object Load : SearchUiAction()
    data object SearchResult: SearchUiAction()
    data class MovieClicked(val id: Int) : SearchUiAction()
    data class SearchQueryChanged(val query : String): SearchUiAction()
}