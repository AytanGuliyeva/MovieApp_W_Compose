package com.example.movieapp_w_compose.features.presentation.signIn

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class SignInSingleEvent : UiSingleEvent {
    data object OpenSignUpScreen : SignInSingleEvent()
    data object OpenHomeScreen : SignInSingleEvent()
}
