package com.example.movieapp_w_compose.features.presentation.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_w_compose.retrofit.model.Movie
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun MovieItem(
    //movie: Movie,
    title:String,
    posterPath:String,
) {
//    val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    val posterUrl = "https://image.tmdb.org/t/p/w500${posterPath}"

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
