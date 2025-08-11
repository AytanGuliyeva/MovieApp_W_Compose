package com.example.movieapp_w_compose.features.presentation.editProfile

import com.example.movieapp_w_compose.features.presentation.changePassword.ChangePasswordUiAction
import com.example.movieapp_w_compose.state.UiAction

sealed class EditProfileUiAction :UiAction{
    data object Load : EditProfileUiAction()
    data object BackToProfile : EditProfileUiAction()
}