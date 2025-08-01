package com.example.movieapp_w_compose.features.presentation.signIn

sealed class SignInUiAction {
    data object Load : SignInUiAction()
    data object CreateOneClick : SignInUiAction()
    data object SignInClick : SignInUiAction()
}
