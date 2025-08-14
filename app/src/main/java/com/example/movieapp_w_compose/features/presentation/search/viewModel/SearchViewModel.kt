package com.example.movieapp_w_compose.features.presentation.search.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.features.presentation.search.state.SearchSingleEvent
import com.example.movieapp_w_compose.features.presentation.search.state.SearchState
import com.example.movieapp_w_compose.features.presentation.search.state.SearchUiAction
import com.example.movieapp_w_compose.retrofit.Repository
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : MviViewModel<
        SearchState,
        UiState<SearchState>,
        SearchUiAction,
        SearchSingleEvent
        >() {

    init {
        handleAction(SearchUiAction.Load)
    }

    override fun initState(): UiState<SearchState> = UiState.Loading

    override fun handleAction(action: SearchUiAction) {
        when(action){
            is SearchUiAction.Load -> {
                getMovies()
            }

            is SearchUiAction.SearchResult -> {
                getSearch(currentState().query)
            }
            is SearchUiAction.SearchQueryChanged -> {
                val newState = currentState().copy(query = action.query)
                submitState(UiState.Success(newState))
               handleAction(SearchUiAction.SearchResult)
            }
            is SearchUiAction.MovieClicked -> {
                submitSingleEvent(SearchSingleEvent.OpenDetailMovieScreen(action.id))
            }
        }
    }
    private fun currentState(): SearchState {
        return (uiStateFlow.value as? UiState.Success)?.data ?: SearchState()
    }
    private fun getSearch(query : String){
        viewModelScope.launch {
            try {
                val response = repository.getSearch(query)
                    val movies =  response?.results ?: emptyList()
                    val current  = currentState()
                    val newState =  current.copy(movie = movies)
                    submitState(UiState.Success(newState))
            }catch ( e:Exception){
                Log.e("SearchViewModel", "Exception: ${e.message}")
            }
        }
    }
    private fun getMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getMovies()
                 val movies = response?.results ?: emptyList()
                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: SearchState()
                    val newState =  currentState.copy(
                        movie= movies
                    )
                    submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")

            }
        }
    }
}