package com.example.movieapp_w_compose.features.presentation.profile

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.User

@Immutable
data class ProfileState (
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLogoutDialogVisible: Boolean = false
)