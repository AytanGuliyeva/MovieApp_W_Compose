package com.example.movieapp_w_compose.features.presentation.editProfile

import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
) : MviViewModel<EditProfileState,
        UiState<EditProfileState>,
        EditProfileUiAction,
        EditProfileSingleEvent>() {
    override fun initState(): UiState<EditProfileState> = UiState.Loading

    override fun handleAction(action: EditProfileUiAction) {
        when(action){
            is EditProfileUiAction.Load ->{

            }
            is EditProfileUiAction.BackToProfile -> {
                submitSingleEvent(EditProfileSingleEvent.OpenProfileScreen)
            }
        }
    }
}