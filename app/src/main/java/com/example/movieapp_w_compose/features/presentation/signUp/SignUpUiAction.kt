package com.example.movieapp_w_compose.features.presentation.signUp


sealed class SignUpUiAction {
    data object Load : SignUpUiAction()
    data object BackToSignInClick : SignUpUiAction()
    data object SignUpClick : SignUpUiAction()
}