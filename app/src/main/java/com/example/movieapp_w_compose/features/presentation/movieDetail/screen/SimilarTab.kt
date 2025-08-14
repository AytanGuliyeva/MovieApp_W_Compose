package com.example.movieapp_w_compose.features.presentation.movieDetail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen
import androidx.compose.foundation.lazy.items
import com.example.movieapp_w_compose.features.presentation.movieDetail.components.SimilarMovieItem
import com.example.movieapp_w_compose.features.presentation.movieDetail.viewModel.MovieDetailViewModel

@Composable
fun SimilarTab(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    CommonScreen(state = uiState) { state ->
        val list = state.similarMovies

        if (list.isEmpty()) {
            Text(
                text = "No similar movies",
                modifier = Modifier.padding(16.dp),
                style = MovieAppTheme.typograph.subtitleMedium,
                color = MovieAppTheme.colors.red
            )
            return@CommonScreen
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list, key = { it.id }) { movie ->
                SimilarMovieItem(
                    title = movie.title,
                    posterPath = movie.posterPath,
                    overview = movie.overview,
                    onClick = { }
                )
            }
        }
    }
}
