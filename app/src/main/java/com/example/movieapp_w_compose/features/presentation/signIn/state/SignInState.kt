package com.example.movieapp_w_compose.features.presentation.signIn.state

import androidx.compose.runtime.Immutable

@Immutable
data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)
