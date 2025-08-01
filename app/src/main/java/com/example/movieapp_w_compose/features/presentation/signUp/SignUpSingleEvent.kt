package com.example.movieapp_w_compose.features.presentation.signUp

sealed class SignUpSingleEvent {
    data object OpenSignInScreen : SignUpSingleEvent()
    data object OpenHomeScreen:SignUpSingleEvent()
}