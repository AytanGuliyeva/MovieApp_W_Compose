package com.example.movieapp_w_compose.features.navigation

sealed class NotificationDestination {
    data object SplashScreen : NotificationDestination()
    data object SignIn : NotificationDestination()
    data object SignUp : NotificationDestination()
}
