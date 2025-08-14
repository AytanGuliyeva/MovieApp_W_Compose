package com.example.movieapp_w_compose.features.presentation.movieDetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.movieDetail.components.ReviewItem
import com.example.movieapp_w_compose.features.presentation.movieDetail.components.SimilarMovieItem
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailSingleEvent
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailUiAction
import com.example.movieapp_w_compose.features.presentation.movieDetail.viewModel.MovieDetailViewModel
import com.example.movieapp_w_compose.features.presentation.movieDetail.components.TrailerWebView
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    backStack: MutableList<MovieDestination>,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.handleAction(MovieDetailUiAction.Load(movieId))
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is MovieDetailSingleEvent.OpenHomeScreen ->
                    backStack.removeLastOrNull()
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MovieAppTheme.colors.mainColor,
                    navigationIconContentColor = MovieAppTheme.colors.red
                ),
                navigationIcon = {
                    IconButton(onClick = { viewModel.handleAction(MovieDetailUiAction.BackToHomeClick) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.back),
                            tint = MovieAppTheme.colors.red
                        )
                    }
                },
                title = {},
                actions = {}
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { inner ->
        CommonScreen(state = uiState) { state ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieAppTheme.colors.mainColor)
                    .padding(inner),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {
                        AsyncImage(
                            model = state.posterUrl,
                            contentDescription = stringResource(R.string.picture),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                        text = state.title,
                        style = MovieAppTheme.typograph.titleMedium.copy(fontWeight = FontWeight.Bold),

                        )
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                        text = state.year,
                        style = MovieAppTheme.typograph.footnoteRegular
                    )
                }
                item {
                    Button(
                        onClick = { viewModel.handleAction(MovieDetailUiAction.PlayToggle) },
                        colors = ButtonDefaults.buttonColors(containerColor = MovieAppTheme.colors.red),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = if (state.isTrailerVisible) stringResource(R.string.hide_trailer) else stringResource(
                                R.string.play
                            )
                        )
                    }
                }

                if (state.trailerUrl != null && state.isTrailerVisible) {
                    item {
                        TrailerWebView(
                            url = state.trailerUrl,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
                stickyHeader {
                    TabRow(
                        selectedTabIndex = state.selectedTabIndex,
                        modifier = Modifier.background(MovieAppTheme.colors.menuColor)
                    ) {
                        listOf("Episode", "Similar", "About").forEachIndexed { index, title ->
                            Tab(
                                selected = index == state.selectedTabIndex,
                                onClick = {
                                    viewModel.handleAction(
                                        MovieDetailUiAction.TabSelected(
                                            index
                                        )
                                    )
                                },
                                modifier = Modifier.background(MovieAppTheme.colors.menuColor),
                                text = { Text(title, color = MovieAppTheme.colors.red) }
                            )
                        }
                    }
                }

                when (state.selectedTabIndex) {
                    0 -> {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clickable { viewModel.handleAction(MovieDetailUiAction.PlayToggle) },
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = MovieAppTheme.colors.detailColor)
                            ) {
                                Box(Modifier.fillMaxSize()) {
                                    Row(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AsyncImage(
                                            model = state.posterUrl,
                                            contentDescription = "picture",
                                            modifier = Modifier
                                                .width(150.dp)
                                                .height(145.dp),
                                            contentScale = ContentScale.FillWidth
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .fillMaxSize(),
                                            verticalArrangement = Arrangement.Top
                                        ) {
                                            Text(
                                                text = "Trailer",
                                                color = MovieAppTheme.colors.white,
                                                style = MovieAppTheme.typograph.titleMedium
                                            )
                                            Text(
                                                text = state.overview,
                                                style = MovieAppTheme.typograph.footnoteRegular,
                                                maxLines = 3,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        item {
                            Text(
                                text = "Reviews",
                                style = MovieAppTheme.typograph.titleMedium,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                            )
                        }
                        items(state.reviews, key = { it.id }) { review ->
                            ReviewItem(item = review)
                        }
                        if (state.reviews.isEmpty()) {
                            item {
                                Text(
                                    text = "No Reviews",
                                    modifier = Modifier.padding(16.dp),
                                    style = MovieAppTheme.typograph.subtitleMedium,
                                    color = MovieAppTheme.colors.red
                                )
                            }
                        }
                    }

                    1 -> {
                        items(state.similarMovie, key = { it.id }) { movie ->
                            SimilarMovieItem(
                                title = movie.title,
                                posterPath = movie.posterPath,
                                overview = movie.overview,
                                onClick = { }
                            )
                        }
                        if (state.similarMovie.isEmpty()) {
                            item {
                                Text(
                                    text = "No similar movies",
                                    modifier = Modifier.padding(16.dp),
                                    style = MovieAppTheme.typograph.subtitleMedium,
                                    color = MovieAppTheme.colors.red
                                )
                            }
                        }
                    }

                    2 -> {
                        item {
                            Text(
                                text = state.overview,
                                style = MovieAppTheme.typograph.footnoteRegular,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
                /* when (state.selectedTabIndex) {
                     0 -> {
                         item { EpisodeTab() }
                     }

                     1 -> {
                         item { SimilarTab() }
                     }

                     2 -> {
                         item { AboutTab(overview = state.overview) }
                     }
                 }*/
                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
}