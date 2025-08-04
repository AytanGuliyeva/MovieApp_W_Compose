package com.example.movieapp_w_compose.features.presentation.signUp

import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.state.UiAction


sealed class SignUpUiAction :UiAction{
    data object Load : SignUpUiAction()
    data object BackToSignInClick : SignUpUiAction()
    data class SignUpClick (val input :User): SignUpUiAction()
}