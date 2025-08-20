package com.example.movieapp_w_compose.features.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun ProfileItem(
    modifier: Modifier = Modifier,
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
    textStyle: TextStyle = MovieAppTheme.typograph.calloutMedium
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = MovieAppTheme.colors.red
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = textStyle,
                color = MovieAppTheme.colors.white,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = com.example.movieapp_w_compose.R.drawable.ic_next),
                contentDescription = null,
                tint = MovieAppTheme.colors.red
            )
        }

        Divider(
            color = MovieAppTheme.colors.grey2,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
