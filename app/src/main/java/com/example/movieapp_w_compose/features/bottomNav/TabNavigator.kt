package com.example.movieapp_w_compose.features.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.movieapp_w_compose.features.presentation.home.screen.HomeScreenContent
import com.example.movieapp_w_compose.features.presentation.movieDetail.screen.MovieDetailScreen
import com.example.movieapp_w_compose.features.presentation.profile.screen.ProfileScreen
import com.example.movieapp_w_compose.features.presentation.search.screen.SearchScreen
import com.example.movieapp_w_compose.features.presentation.signIn.screen.SignInScreen
import com.example.movieapp_w_compose.features.presentation.signUp.screen.SignUpScreen
import com.example.movieapp_w_compose.features.presentation.splash.SplashScreen

@Composable
fun TabNavigator() {
    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    val backStack = remember {
        mutableStateListOf<MovieDestination>(MovieDestination.SplashScreen)

    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it
                backStack.add(selectedItem.route)}
            )
        }
    ) { padding ->
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { destination ->
                when (destination) {
                    is MovieDestination.SplashScreen -> NavEntry(destination) {
                        SplashScreen(backStack)
                    }

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
                        ProfileScreen(backStack,{})
                    }

                    is MovieDestination.MovieDetail -> NavEntry(destination) {
                        MovieDetailScreen(
                            movieId = destination.movieId,
                            backStack = backStack
                        )
                    }
                }
            }
        )
    }
}
