package com.example.movieapp_w_compose.features.presentation.search

import com.example.movieapp_w_compose.features.presentation.signIn.SignInUiAction
import com.example.movieapp_w_compose.state.UiAction

sealed class SearchUiAction :UiAction{
    data object Load : SearchUiAction()
  //  data object MovieResult:SearchUiAction()
    data object SearchResult:SearchUiAction()
    data class SearchQueryChanged(val query : String): SearchUiAction()
}