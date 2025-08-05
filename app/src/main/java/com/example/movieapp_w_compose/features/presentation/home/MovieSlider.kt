package com.example.movieapp_w_compose.features.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieSlider(
    imageUrls: List<String>
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = imageUrls.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) { page ->
            AsyncImage(
                model = imageUrls[page],
                contentDescription = "Slider image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Crop
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = androidx.compose.ui.graphics.Color.Red,
            inactiveColor = androidx.compose.ui.graphics.Color.LightGray,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}
