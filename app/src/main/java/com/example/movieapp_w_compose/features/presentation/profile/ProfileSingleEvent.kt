package com.example.movieapp_w_compose.features.presentation.profile

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class ProfileSingleEvent :UiSingleEvent{
    data object NavigateToLogin : ProfileSingleEvent()
    //change password
}