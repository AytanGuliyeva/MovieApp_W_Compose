package com.example.movieapp_w_compose.features.presentation.changePassword

data class ChangePasswordState(
    val oldPasswordFromServer: String = "",

    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",

    val currentPasswordError: String? = null,
    val newPasswordError: String? = null,
    val confirmPasswordError: String? = null,

    val isLoading: Boolean = false,
    val isPasswordChanged: Boolean = false,
    val canSubmit: Boolean = false
)
