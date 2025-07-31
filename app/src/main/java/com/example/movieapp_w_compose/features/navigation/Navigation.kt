package com.example.movieapp_w_compose.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp_w_compose.features.presentation.SignIn.SignInScreen
import com.example.movieapp_w_compose.features.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val backStack = remember {
        mutableStateListOf<NotificationDestination>(NotificationDestination.SplashScreen)

    }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { destination ->
            when (destination) {
                is NotificationDestination.SplashScreen -> NavEntry(destination) {
                    SplashScreen(backStack)
                }

                is NotificationDestination.SignIn -> NavEntry(destination) {
                    SignInScreen()
                }

                is NotificationDestination.SignUp -> NavEntry(destination) {
                    //SignUpScreen()
                }
            }
        }
    )
}