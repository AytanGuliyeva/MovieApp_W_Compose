package com.example.movieapp_w_compose.features.presentation.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.features.bottomNav.TabNavigator
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.LocalAppTypograph
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen


@Composable
fun HomeScreen() {
    TabNavigator()
}


@Composable
fun HomeScreenContent(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleAction(HomeUiAction.Load)
        viewModel.handleAction(HomeUiAction.NowPlayingMovies)
    }

    CommonScreen(state = state) { homeState ->
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
                text = "Categories",
                style = LocalAppTypograph.current.titleMedium
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(10.dp)
            ) {
                // genre chip list
            }

            Text(
                text = "Most Popular",
                style = LocalAppTypograph.current.titleMedium,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
                items(10) {
                    // MovieItem()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Top Rated", style = LocalAppTypograph.current.titleMedium)
                Text("See all", style = LocalAppTypograph.current.subtitleMedium)
            }

            LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
                items(10) {
                    // MovieItem()
                }
            }
        }
    }
}
