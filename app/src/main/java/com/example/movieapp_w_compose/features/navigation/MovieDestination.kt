package com.example.movieapp_w_compose.features.navigation

sealed class MovieDestination {
    data object SplashScreen : MovieDestination()
    data object SignIn : MovieDestination()
    data object SignUp : MovieDestination()
    data object Home : MovieDestination()
}
