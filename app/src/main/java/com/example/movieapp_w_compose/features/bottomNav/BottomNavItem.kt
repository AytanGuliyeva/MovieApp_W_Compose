package com.example.movieapp_w_compose.features.bottomNav

import com.example.movieapp_w_compose.R

sealed class BottomNavItem(val title: String, val icon: Int) {
    object Home : BottomNavItem("Home", R.drawable.ic_home)
    object Search : BottomNavItem("Search", R.drawable.ic_search)
    object Profile : BottomNavItem("Profile", R.drawable.ic_user)
}