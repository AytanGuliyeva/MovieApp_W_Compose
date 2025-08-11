package com.example.movieapp_w_compose.features.presentation.editProfile

data class EditProfileState(

    val initialUsername: String = "",
    val initialImageUrl: String = "",

    val username: String = "",
    val usernameError: String? = null,

    val selectedImageUri: String? = null,
    val previewImageUrl: String = "",

    val isLoading: Boolean = false,
    val canSave: Boolean = false,
    val isSaved: Boolean = false
)