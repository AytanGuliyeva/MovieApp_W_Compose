package com.example.movieapp_w_compose.features.presentation.movieDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

@Composable
fun SimilarMovieItem(
    title: String,
    posterPath: String?,
    overview: String,
    onClick: () -> Unit = {}
) {
    val posterUrl = posterPath.let { "https://image.tmdb.org/t/p/w342$it" }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = title,
            modifier = Modifier
                .width(100.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                color = MovieAppTheme.colors.white,
                style = MovieAppTheme.typograph.subtitleMedium
            )
            Text(
                text = overview,
                color = MovieAppTheme.colors.white,
                style = MovieAppTheme.typograph.footnoteRegular,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
