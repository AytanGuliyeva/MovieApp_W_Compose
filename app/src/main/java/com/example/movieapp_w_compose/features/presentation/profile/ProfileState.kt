package com.example.movieapp_w_compose.features.presentation.profile

import com.example.movieapp_w_compose.data.User

data class ProfileState (
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLogoutDialogVisible: Boolean = false
)