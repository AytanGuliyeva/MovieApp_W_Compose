package com.example.movieapp_w_compose.features.navigation

import androidx.navigation3.runtime.NavKey

sealed class MovieDestination:NavKey {
  //  data object SplashScreen : MovieDestination()
    data object SignIn : MovieDestination()
    data object SignUp : MovieDestination()
    data object Home : MovieDestination()
    data object Search : MovieDestination()
    data object Profile : MovieDestination()
    data object EditProfile : MovieDestination()
    data object ChangePassword : MovieDestination()
    data class MovieDetail(val movieId: Int) : MovieDestination()
}
