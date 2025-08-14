package com.example.movieapp_w_compose.features.bottomNav

import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination

sealed class BottomNavItem(val title: String, val icon: Int,val route: MovieDestination) {

    data object Home : BottomNavItem("Home", R.drawable.ic_home, route = MovieDestination.Home)
    data object Search : BottomNavItem("Search", R.drawable.ic_search, route = MovieDestination.Search)
    data object Profile : BottomNavItem("Profile", R.drawable.ic_user, route = MovieDestination.Profile)
}