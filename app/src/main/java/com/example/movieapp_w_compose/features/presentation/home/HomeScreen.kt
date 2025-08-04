package com.example.movieapp_w_compose.features.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieapp_w_compose.features.bottomNav.BottomNavItem
import com.example.movieapp_w_compose.features.bottomNav.BottomNavigationBar
import com.example.movieapp_w_compose.features.bottomNav.HomeTab
import com.example.movieapp_w_compose.features.bottomNav.ProfileTab
import com.example.movieapp_w_compose.features.bottomNav.SearchTab
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun HomeScreen() {
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    Scaffold(bottomBar = {

        BottomNavigationBar(selectedItem = selectedItem) { item ->
            selectedItem = item
        }
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedItem) {
                BottomNavItem.Home -> HomeTab()
                BottomNavItem.Search -> SearchTab()
                BottomNavItem.Profile -> ProfileTab()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ){}
    }
}
