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
) : MviViewModel<
        HomeState,
        UiState<HomeState>,
        HomeUiAction,
        HomeSingleEvent
        >() {

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
            is HomeUiAction.GenreSelected -> getMoviesByGenre( action.genreId)
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getTopRatedMovies()
                if (response.isSuccessful) {

                    val movies = response.body()?.results ?: emptyList()
                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                    val newState = currentState.copy(
                        topRatedMovies = movies
                    )
                    submitState(UiState.Success(newState))
                    Log.d("HomeViewModel", "Fetched top rated movies: ${response.body()}")
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularMovies()
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()
                    val newState = currentState.copy(
                        popularMovies = movies
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

    private fun getMoviesByGenre( genreId: Int?) {
        viewModelScope.launch {
            try {
                val response = repository.getCategoryMovies( genreId)
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()

                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()

                    val newState = currentState.copy(
                        genreMovies = movies,
                        selectedGenreId = genreId
                    )

                    submitState(UiState.Success(newState))
                    Log.d("HomeViewModel", "Fetched genre movies: ${response.body()}")
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                val response = repository.getGenres()
                if (response.isSuccessful) {
                    val genres = response.body()?.genres ?: emptyList()
                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()

                    val newState = currentState.copy(
                        genres = genres
                    )

                    submitState(UiState.Success(newState))
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                    submitState(UiState.Error("Error: ${response.code()}"))
                }
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
                if (response.isSuccessful) {
                    val urls = response.body()?.results
                        ?.take(5)
                        ?.mapNotNull { it?.posterPath }
                        ?.map { "https://image.tmdb.org/t/p/w500$it" }
                        ?: emptyList()

                    val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: HomeState()

                    val newState = currentState.copy(
                        sliderImageUrls = urls
                    )

                    submitState(UiState.Success(newState))
                    Log.d("HomeViewModel", "Fetched now playing movies: ${response.body()}")
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()}")
                    submitState(UiState.Error("Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Exception: ${e.message}")
                submitState(UiState.Error("Exception: ${e.localizedMessage}"))
            }
        }
    }
}

