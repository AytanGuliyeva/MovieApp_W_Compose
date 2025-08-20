package com.example.movieapp_w_compose.features.bottomNav

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.changePassword.screen.ChangePasswordScreen
import com.example.movieapp_w_compose.features.presentation.editProfile.screen.EditProfileScreen
import com.example.movieapp_w_compose.features.presentation.home.screen.HomeScreen
import com.example.movieapp_w_compose.features.presentation.movieDetail.screen.MovieDetailScreen
import com.example.movieapp_w_compose.features.presentation.profile.screen.ProfileScreen
import com.example.movieapp_w_compose.features.presentation.search.screen.SearchScreen
import com.example.movieapp_w_compose.features.presentation.signIn.screen.SignInScreen
import com.example.movieapp_w_compose.features.presentation.signUp.screen.SignUpScreen

@Composable
fun TabNavigator(firstScreen: MovieDestination) {
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    val backStack = remember { mutableStateListOf<MovieDestination>(firstScreen) }
    val currentDest = backStack.lastOrNull()
    val showBottomBar =
        currentDest is MovieDestination.Home ||
                currentDest is MovieDestination.Search ||
                currentDest is MovieDestination.Profile

    LaunchedEffect(currentDest) {
        selectedItem = when (currentDest) {
            is MovieDestination.Home -> BottomNavItem.Home
            is MovieDestination.Search -> BottomNavItem.Search
            is MovieDestination.Profile -> BottomNavItem.Profile
            else -> selectedItem
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    selectedItem = selectedItem,
                    onItemSelected = { item ->
                        selectedItem = item
                        if (backStack.lastOrNull() != item.route) {
                            backStack.add(item.route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = { destination ->
                    when (destination) {
//                        is MovieDestination.SplashScreen -> NavEntry(destination) {
//                            SplashScreen(backStack)
//                        }

                        is MovieDestination.SignIn -> NavEntry(destination) {
                            SignInScreen(backStack)
                        }

                        is MovieDestination.SignUp -> NavEntry(destination) {
                            SignUpScreen(backStack)
                        }

                        is MovieDestination.Home -> NavEntry(destination) {
                            HomeScreen(backStack)
                        }

                        is MovieDestination.EditProfile -> NavEntry(destination) {
                            EditProfileScreen(backStack)
                        }

                        is MovieDestination.ChangePassword -> NavEntry(destination) {
                            ChangePasswordScreen(backStack)
                        }

                        is MovieDestination.Search -> NavEntry(destination) {
                            SearchScreen(backStack)
                        }

                        is MovieDestination.Profile -> NavEntry(destination) {
                            ProfileScreen(
                                backStack,
                                onLogout = {
                                    backStack.clear()
                                    backStack.add(MovieDestination.SignIn)
                                }
                            )
                        }

                        is MovieDestination.MovieDetail -> NavEntry(destination) {
                            MovieDetailScreen(movieId = destination.movieId, backStack = backStack)
                        }
                    }
                }
            )
        }
    }
}
