package com.example.movieapp_w_compose.features.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.base.ConstValues
import com.example.movieapp_w_compose.features.presentation.signIn.SignInSingleEvent
import com.example.movieapp_w_compose.features.presentation.signIn.SignInUiAction
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

    //private val repository = Repository()


    init {
        handleAction(HomeUiAction.Load)
    }

    override fun initState(): UiState<HomeState> = UiState.Loading

    override fun handleAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.Load -> {
                handleAction(HomeUiAction.NowPlayingMovies)
            }

            is HomeUiAction.NowPlayingMovies -> {
                getNowPlayingMovies(ConstValues.API_TOKEN)
            }
        }
    }

    private fun getNowPlayingMovies(apiKey: String) {
        submitState(UiState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getNowPlayingMovies(apiKey)
                if (response.isSuccessful) {
                    val urls = response.body()?.results
                        ?.take(5)
                        ?.mapNotNull { it.poster_path }
                        ?.map { "https://image.tmdb.org/t/p/w500$it" }
                        ?: emptyList()

                    submitState(UiState.Success(HomeState(sliderImageUrls = urls)))
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

