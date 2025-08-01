package com.example.movieapp_w_compose.features.presentation.signUp

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class SignUpSingleEvent:UiSingleEvent {
    data object OpenSignInScreen : SignUpSingleEvent()
    data object OpenHomeScreen:SignUpSingleEvent()
}