package com.example.movieapp_w_compose.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp_w_compose.features.presentation.changePassword.ChangePassword
import com.example.movieapp_w_compose.features.presentation.editProfile.EditProfile
import com.example.movieapp_w_compose.features.presentation.home.HomeScreen
import com.example.movieapp_w_compose.features.presentation.signIn.SignInScreen
import com.example.movieapp_w_compose.features.presentation.signUp.SignUpScreen
import com.example.movieapp_w_compose.features.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val backStack = remember {
        mutableStateListOf<MovieDestination>(MovieDestination.SplashScreen)

    }
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
                    EditProfile(backStack)
                }

                is MovieDestination.ChangePassword -> NavEntry(destination) {
                    ChangePassword(backStack)
                }
            }
        }
    )
}