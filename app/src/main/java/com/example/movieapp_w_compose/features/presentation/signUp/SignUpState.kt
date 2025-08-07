package com.example.movieapp_w_compose.features.presentation.signUp

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
