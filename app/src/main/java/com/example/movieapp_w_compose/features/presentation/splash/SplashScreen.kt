package com.example.movieapp_w_compose.features.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.NotificationDestination
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(backStack: SnapshotStateList<NotificationDestination>){
    LaunchedEffect (Unit){
        delay(1000L)
        backStack.removeLastOrNull()
        backStack.add(NotificationDestination.SignIn)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MovieAppTheme.colors.red),
        contentAlignment = Alignment.Center)
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.ic_movie_app),
                contentDescription = "App icon",
                modifier = Modifier.size(100.dp))

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.welcome_to_the_world_of_movies),
                style = MovieAppTheme.typograph.calloutMedium
            )
        }
    }
}