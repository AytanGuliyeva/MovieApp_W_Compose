package com.example.movieapp_w_compose.features.presentation.movieDetail.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailSingleEvent
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailUiAction
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailState
import com.example.movieapp_w_compose.retrofit.Repository
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val firestore: FirebaseFirestore,
    private val repository: Repository
) : MviViewModel<MovieDetailState, UiState<MovieDetailState>, MovieDetailUiAction,
        MovieDetailSingleEvent>() {
    override fun initState(): UiState<MovieDetailState> = UiState.Loading


    override fun handleAction(action: MovieDetailUiAction) {

        when (action) {
            is MovieDetailUiAction.Load -> {
                loadById(action.movieId)
                getReviews(action.movieId)
                getSimilar(action.movieId)
                getMovieTrailer(action.movieId)
            }

            is MovieDetailUiAction.TabSelected -> {
                val current = (uiStateFlow.value as? UiState.Success)?.data ?: return
                submitState(UiState.Success(current.copy(selectedTabIndex = action.index)))
            }
            MovieDetailUiAction.PlayToggle -> {
                val current = (uiStateFlow.value as? UiState.Success)?.data ?: return
                submitState(UiState.Success(current.copy(isTrailerVisible = !current.isTrailerVisible)))
            }

            MovieDetailUiAction.BackToHomeClick -> submitSingleEvent(MovieDetailSingleEvent.OpenHomeScreen)
        }
    }

    private fun getMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getVideos(movieId)
                    val videos = response?.results.orEmpty()
                    val chosen = videos.firstOrNull { it.site.equals("YouTube", true) && it.type.equals("Trailer", true) }
                        ?: videos.firstOrNull { it.site.equals("YouTube", true) }
                        ?: videos.firstOrNull()

                    val url = chosen?.key?.let { "https://www.youtube.com/embed/$it"}

                    val current = (uiStateFlow.value as? UiState.Success)?.data
                    if (current != null) {
                        submitState(UiState.Success(current.copy(trailerUrl = url)))
                    }
            } catch (e: Exception) {
                Log.e("Trailer", "Error getting trailer: $e")
            }
        }
    }

    private fun loadById(movieId: Int) {
        submitState(UiState.Success(MovieDetailState(isLoading = true)))

        viewModelScope.launch {
            try {
                val response = repository.getMovieById(movieId)
                    val movie = response
                    if (movie == null) {
                        submitState(UiState.Success(MovieDetailState(error = "Empty body")))
                        return@launch
                    }
                    val posterUrl =
                        movie.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }.orEmpty()
                    submitState(
                        UiState.Success(
                            MovieDetailState(
                                isLoading = false,
                                movieId = movie.id,
                                title = movie.title,
                                year = movie.releaseDate,
                                overview = movie.overview,
                                posterUrl = posterUrl
                            )
                        )
                    )
            } catch (t: Throwable) {
                submitState(
                    UiState.Success(
                        MovieDetailState(
                            error = t.localizedMessage ?: "Unknown error"
                        )
                    )
                )
            }
        }
    }

    private fun getReviews(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getReviews(movieId)
                    val reviews = response?.results ?: emptyList()
                    val current = (uiStateFlow.value as? UiState.Success)?.data
                    if (current != null) {
                        submitState(UiState.Success(current.copy(reviews = reviews)))
                        Log.e("Reviews", "API response : ${reviews}")
                    }
            } catch (e: Exception) {
                Log.e("Reviews", "Error getting reviews: $e")
            }
        }
    }

    private fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getSimilar(movieId)
                    val movies = response?.results ?: emptyList()
                    val currentState =
                        (uiStateFlow.value as? UiState.Success)?.data ?: MovieDetailState()
                    val newState = currentState.copy(
                        similarMovie = movies
                    )
                    submitState(UiState.Success(newState))
            } catch (e: Exception) {
                Log.e("similarMovies", "Error getting reviews: $e")
            }
        }
    }
}