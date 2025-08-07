package com.example.movieapp_w_compose.features.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.HomeScreen
import com.example.movieapp_w_compose.features.presentation.home.HomeScreenContent
import com.example.movieapp_w_compose.features.presentation.profile.ProfileScreen
import com.example.movieapp_w_compose.features.presentation.search.SearchScreen

@Composable
fun TabNavigator(backStack: MutableList<MovieDestination>) {
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedItem) {
                BottomNavItem.Home -> HomeScreenContent()
                BottomNavItem.Search -> SearchScreen()
                BottomNavItem.Profile -> ProfileScreen(
                    backStack = backStack,
                    onLogout = {
                        backStack.clear()
                        backStack.add(MovieDestination.SignIn)
                    }
                )
            }
        }
    }
}
