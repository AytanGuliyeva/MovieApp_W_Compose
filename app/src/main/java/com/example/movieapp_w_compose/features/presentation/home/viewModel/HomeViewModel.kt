package com.example.movieapp_w_compose.features.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.features.presentation.home.state.HomeSingleEvent
import com.example.movieapp_w_compose.features.presentation.home.state.HomeState
import com.example.movieapp_w_compose.features.presentation.home.state.HomeUiAction
import com.example.movieapp_w_compose.retrofit.Repository
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : MviViewModel<HomeState,
        UiState<HomeState>,
        HomeUiAction,
        HomeSingleEvent>() {

    init {
        handleAction(HomeUiAction.Load)
    }

    override fun initState(): UiState<HomeState> = UiState.Loading

    override fun handleAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.Load -> {
                getNowPlayingMovies()
                getGenres()
                getPopularMovies()
                getTopRatedMovies()
            }

            is HomeUiAction.MovieClicked -> {
                submitSingleEvent(HomeSingleEvent.OpenDetailMovieScreen(action.id))
            }

            is HomeUiAction.GenreSelected -> getMoviesByGenre(action.genreId)
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getTopRatedMovies()
                val movies = response?.results ?: emptyList()
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                val newState = currentState.copy(
                    topRatedMovie = movies
                )
                submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularMovies()
                val movies = response?.results ?: emptyList()
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                val newState = currentState.copy(
                    popularMovie = movies
                )
                submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
            }
        }
    }

    private fun getMoviesByGenre(genreId: Int?) {
        viewModelScope.launch {
            try {
                val response = repository.getCategoryMovies(genreId)
                val movies = response?.results ?: emptyList()
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                val newState = currentState.copy(
                    genreMovie = movies,
                    selectedGenreId = genreId
                )
                submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                val response = repository.getGenres()
                val genres = response?.genres ?: emptyList()
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                val newState = currentState.copy(
                    genres = genres
                )
                submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
                submitState(UiState.Error("Exception: ${e.localizedMessage}"))
            }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getNowPlayingMovies()
                val urls = response?.results
                    ?.take(5)
                    ?.mapNotNull { it?.posterPath }
                    ?.map { "https://image.tmdb.org/t/p/w500$it" }
                    ?: emptyList()

                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                val newState = currentState.copy(
                    sliderImageUrls = urls
                )
                submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
                submitState(UiState.Error("Exception: ${e.localizedMessage}"))
            }
        }
    }
}

