package com.example.movieapp_w_compose.features.presentation.editProfile

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class EditProfileSingleEvent:UiSingleEvent{
    data object OpenProfileScreen : EditProfileSingleEvent()
}