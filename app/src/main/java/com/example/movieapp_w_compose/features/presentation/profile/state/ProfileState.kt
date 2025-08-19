package com.example.movieapp_w_compose.features.presentation.profile.state

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.data.Languages

@Immutable
data class ProfileState (
    val user: User? = null,
    val localImageUri: String = "",
    val isLogoutDialogVisible: Boolean = false,
    val languageOption: Languages = Languages.EN
)