package com.example.movieapp_w_compose.features.presentation.movieDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.data.domain.ReviewEntity
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.data.dto.ReviewDTO

@Composable
fun ReviewItem(item: ReviewEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MovieAppTheme.colors.detailColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = remember(item.author) { item.author.take(1).uppercase() },
                color = Color.White,
                style = MovieAppTheme.typograph.subtitleMedium
            )
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = item.author,
                color = MovieAppTheme.colors.white,
                style = MovieAppTheme.typograph.subtitleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = item.content,
                color = MovieAppTheme.colors.white,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}