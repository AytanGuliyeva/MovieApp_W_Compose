package com.example.movieapp_w_compose.features.presentation.changePassword

import com.example.movieapp_w_compose.state.UiSingleEvent

sealed class ChangePasswordSingleEvent:UiSingleEvent{
    data object OpenProfileScreen : ChangePasswordSingleEvent()
    data class ShowMessage(val msg: String) : ChangePasswordSingleEvent()

}