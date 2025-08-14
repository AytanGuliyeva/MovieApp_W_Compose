package com.example.movieapp_w_compose.features.presentation.profile.state

import com.example.movieapp_w_compose.data.Languages
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class ProfileSingleEvent : UiSingleEvent {
    data object NavigateToLogin : ProfileSingleEvent()
    data object NavigateToEditProfile : ProfileSingleEvent()
    data object NavigateToChangePassword : ProfileSingleEvent()
    data class NavigateToSplash(val lang:Languages): ProfileSingleEvent()
}