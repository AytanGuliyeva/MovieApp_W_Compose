package com.example.movieapp_w_compose.features.presentation.home.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun MovieItem(
    title:String,
    posterPath:String,
    onClick: () -> Unit
) {
    val posterUrl = "https://image.tmdb.org/t/p/w500${posterPath}"

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
