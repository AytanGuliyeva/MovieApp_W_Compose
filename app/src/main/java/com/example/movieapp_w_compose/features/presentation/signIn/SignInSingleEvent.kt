package com.example.movieapp_w_compose.features.presentation.signIn

sealed class SignInSingleEvent {
    data object OpenSignUpScreen : SignInSingleEvent()
    data object OpenHomeScreen : SignInSingleEvent()
}
