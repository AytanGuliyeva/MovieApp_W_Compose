package com.example.movieapp_w_compose.features.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun MovieSlider(
    imageUrls: List<String>
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp).background(MovieAppTheme.colors.mainColor),
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
                contentScale = ContentScale.Fit
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
