package com.example.movieapp_w_compose.features.presentation.profile

import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.state.UiAction

sealed class ProfileUiAction:UiAction {
    data object Load : ProfileUiAction()
    data class LoadUser(val user: User) : ProfileUiAction()
    data object Logout : ProfileUiAction()
    data class ShowLogoutDialog(val show: Boolean) : ProfileUiAction()
}