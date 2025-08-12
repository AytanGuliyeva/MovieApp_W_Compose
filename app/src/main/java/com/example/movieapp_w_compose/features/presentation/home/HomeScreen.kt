package com.example.movieapp_w_compose.features.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.bottomNav.TabNavigator
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.components.ChipItem
import com.example.movieapp_w_compose.features.presentation.home.components.MovieItem
import com.example.movieapp_w_compose.features.presentation.home.components.MovieSlider
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.LocalAppTypograph
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen


@Composable
fun HomeScreen(backStack: MutableList<MovieDestination>) {
    TabNavigator(backStack = backStack)
}

@Composable
fun HomeScreenContent(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiStateFlow.collectAsState()

    CommonScreen(state = state) { homeState ->
        val genreMovies = homeState.genreMovies
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (homeState.sliderImageUrls.isNotEmpty()) {
                MovieSlider(imageUrls = homeState.sliderImageUrls)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp),
                text = stringResource(R.string.categories),
                style = LocalAppTypograph.current.titleMedium,
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                homeState.genres.forEach { genre ->
                    val isSelected = genre.id == homeState.selectedGenreId
                    ChipItem(
                        genre = genre,
                        isSelected = isSelected,
                        onClick = {
                            viewModel.handleAction(HomeUiAction.GenreSelected(genre.id))
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            if (genreMovies.isNotEmpty()) {
                LazyRow {
                    items(genreMovies) { movie ->
                        movie.posterPath?.let {
                            MovieItem(title = movie.title,
                                posterPath = it
                            )
                        }
                    }
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp),
                text = stringResource(R.string.most_popular),
                style = LocalAppTypograph.current.titleMedium,
            )

            LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
                items(homeState.popularMovies) { popularMovies ->
                    popularMovies.posterPath?.let {
                        MovieItem(title = popularMovies.title,
                            posterPath = it
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(R.string.top_rated), style = LocalAppTypograph.current.titleMedium)
            }

            LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
                items(homeState.topRatedMovies) { topRatedMovies ->
                    Log.e("home", "HomeScreenContent: $topRatedMovies")
                    topRatedMovies.posterPath?.let {
                        MovieItem(title = topRatedMovies.title,
                            posterPath = it
                        )
                    }
                }
            }
        }
    }
}
