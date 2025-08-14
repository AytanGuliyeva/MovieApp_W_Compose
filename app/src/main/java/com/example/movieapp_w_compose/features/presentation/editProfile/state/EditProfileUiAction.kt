package com.example.movieapp_w_compose.features.presentation.editProfile.state

import com.example.movieapp_w_compose.state.UiAction

sealed class EditProfileUiAction :UiAction{
    data object Load : EditProfileUiAction()
    data object BackToProfile : EditProfileUiAction()
    data class PictureSelected(val uri: String) : EditProfileUiAction()
    data class UsernameChanged(val value: String) : EditProfileUiAction()
    data object Save : EditProfileUiAction()
}