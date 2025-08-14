package com.example.movieapp_w_compose.features.presentation.changePassword.state

import com.example.movieapp_w_compose.state.UiAction

sealed class ChangePasswordUiAction : UiAction {
    data object Load : ChangePasswordUiAction()
    data object BackToProfileClick : ChangePasswordUiAction()
    data class CurrentChanged(val value: String) : ChangePasswordUiAction()
    data class NewChanged(val value: String) : ChangePasswordUiAction()
    data class ConfirmChanged(val value: String) : ChangePasswordUiAction()
    data object DoneClick : ChangePasswordUiAction()
}