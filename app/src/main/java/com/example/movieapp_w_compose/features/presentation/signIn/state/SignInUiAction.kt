package com.example.movieapp_w_compose.features.presentation.signIn.state

import com.example.movieapp_w_compose.state.UiAction

sealed class SignInUiAction : UiAction {
    data object Load : SignInUiAction()
    data object CreateOneClick : SignInUiAction()
    data class SignInClick(val email: String, val password: String) : SignInUiAction()
    data class EmailChanged(val value: String) : SignInUiAction()
    data class PasswordChanged(val value: String) : SignInUiAction()
    data object ClearForm : SignInUiAction()

}
