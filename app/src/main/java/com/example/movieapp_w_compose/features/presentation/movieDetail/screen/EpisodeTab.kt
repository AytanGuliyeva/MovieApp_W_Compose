package com.example.movieapp_w_compose.features.presentation.movieDetail.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movieapp_w_compose.features.presentation.movieDetail.components.ReviewItem
import com.example.movieapp_w_compose.features.presentation.movieDetail.state.MovieDetailUiAction
import com.example.movieapp_w_compose.features.presentation.movieDetail.viewModel.MovieDetailViewModel
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen
@Composable
fun EpisodeTab(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    CommonScreen(state = uiState) { state ->
        if (state.reviews.isEmpty()) {
            Text(
                text = "No Reviews",
                modifier = Modifier.padding(16.dp),
                style = MovieAppTheme.typograph.subtitleMedium,
                color = MovieAppTheme.colors.red
            )
            return@CommonScreen
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable { viewModel.handleAction(MovieDetailUiAction.PlayToggle) }
                    ,
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
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            items(state.reviews, key = { it.id }) { review ->
                ReviewItem(item = review)
            }
        }
    }
}
