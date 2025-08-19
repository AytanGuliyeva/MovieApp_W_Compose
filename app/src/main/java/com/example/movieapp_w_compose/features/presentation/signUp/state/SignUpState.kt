package com.example.movieapp_w_compose.features.presentation.signUp.state

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpState(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
)
