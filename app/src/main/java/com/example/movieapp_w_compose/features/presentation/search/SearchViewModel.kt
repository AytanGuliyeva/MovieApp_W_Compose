package com.example.movieapp_w_compose.features.presentation.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.base.ConstValues
import com.example.movieapp_w_compose.features.presentation.home.HomeUiAction
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
            is SearchUiAction.Load ->{
                handleAction(SearchUiAction.MovieResult)

            }
            is SearchUiAction.MovieResult -> {
                getMovies(ConstValues.API_TOKEN)

            }
            is SearchUiAction.SearchResult -> {
                getSearch(ConstValues.API_TOKEN,currentState().query)
            }
            is SearchUiAction.SearchQueryChanged -> {
                val newState = currentState().copy(query = action.query)
                submitState(UiState.Success(newState))
                handleAction(SearchUiAction.SearchResult)
            }
        }
    }
    private fun currentState(): SearchState{
        return (uiStateFlow.value as? UiState.Success)?.data ?: SearchState()
    }
    private fun getSearch(apiKey : String, query : String){
        viewModelScope.launch {
            try {
                val response = repository.getSearch(apiKey, query)
                if (response.isSuccessful){
                    val movies =  response.body()?.results ?: emptyList()
                    val current  = currentState()
                    val newState =  current.copy(movies = movies)
                    submitState(UiState.Success(newState))
                    Log.d("SearchViewModel", "Fetched search results: ${response.body()}")

                }
                else{
                    Log.e("SearchViewModel", "Error: ${response.code()}")

                }
            }catch ( e:Exception){

                Log.e("SearchViewModel", "Exception: ${e.message}")

            }
        }
    }
    private fun getMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies(apiKey)
                if (response.isSuccessful) {

                    val movies = response.body()?.results ?: emptyList()
                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: SearchState()
                    val newState =  currentState.copy(
                        movies= movies
                    )
                    submitState(UiState.Success(newState))
                    Log.d("HomeViewModel", "Fetched popular movies: ${response.body()}")
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")

            }
        }
    }
}