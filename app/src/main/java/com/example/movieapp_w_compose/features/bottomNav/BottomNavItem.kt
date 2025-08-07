package com.example.movieapp_w_compose.features.bottomNav

import com.example.movieapp_w_compose.R

sealed class BottomNavItem(val title: String, val icon: Int) {
    data object Home : BottomNavItem("Home", R.drawable.ic_home)
    data object Search : BottomNavItem("Search", R.drawable.ic_search)
    data object Profile : BottomNavItem("Profile", R.drawable.ic_user)
}