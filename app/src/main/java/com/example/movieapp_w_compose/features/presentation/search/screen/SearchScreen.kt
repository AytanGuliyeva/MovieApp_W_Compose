package com.example.movieapp_w_compose.features.presentation.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.components.MovieItem
import com.example.movieapp_w_compose.features.presentation.search.state.SearchSingleEvent
import com.example.movieapp_w_compose.features.presentation.search.state.SearchUiAction
import com.example.movieapp_w_compose.features.presentation.search.viewModel.SearchViewModel
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen

@Composable
fun SearchScreen(backStack: MutableList<MovieDestination>,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiStateFlow.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is SearchSingleEvent.OpenDetailMovieScreen -> {
                    backStack.add(MovieDestination.MovieDetail(event.movieId))
                }
            }
        }
    }
    CommonScreen(state = state) { searchState ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .padding(horizontal = 5.dp)
        ) {
            TextField(
                value = searchState.query,
                onValueChange = {
                    viewModel.handleAction(SearchUiAction.SearchQueryChanged(it))
                },
                placeholder = {
                    Text(stringResource(R.string.looking_for))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(R.string.search),
                        tint = MovieAppTheme.colors.red

                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MovieAppTheme.colors.menuColor,
                    unfocusedContainerColor = MovieAppTheme.colors.menuColor,
                    focusedTextColor = MovieAppTheme.colors.white,
                    unfocusedTextColor = MovieAppTheme.colors.white,
                    cursorColor = MovieAppTheme.colors.red,
                    focusedPlaceholderColor = MovieAppTheme.colors.white,
                    unfocusedPlaceholderColor = MovieAppTheme.colors.white
                )

            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                items(searchState.movie.size) { index ->
                    val movie = searchState.movie[index]
                    movie.posterPath?.let {
                        MovieItem(title = movie.title,
                            posterPath = it,
                            onClick = { viewModel.handleAction(SearchUiAction.MovieClicked(movie.id)) }
                        )
                    }
                }
            }
        }
    }
}
