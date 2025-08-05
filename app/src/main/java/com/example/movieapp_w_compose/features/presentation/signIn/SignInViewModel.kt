package com.example.movieapp_w_compose.features.presentation.signIn

import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val auth: FirebaseAuth) : MviViewModel<
        Unit,
        UiState<Unit>,
        SignInUiAction,
        SignInSingleEvent
        >() {

    init {
        handleAction(SignInUiAction.Load)
    }

    override fun initState(): UiState<Unit> = UiState.Loading

    override fun handleAction(action: SignInUiAction) {
        when (action) {
            is SignInUiAction.Load -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
            }

            is SignInUiAction.CreateOneClick -> {
                submitSingleEvent(SignInSingleEvent.OpenSignUpScreen)
            }

            is SignInUiAction.SignInClick -> {
                signWithEmail(action.email, action.password)
            }
        }
    }

    private fun signWithEmail(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            submitState(UiState.Error("Please fill in both email and password"))
            return
        }
        submitState(UiState.Loading)

        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener {
                submitSingleEvent(SignInSingleEvent.OpenHomeScreen)

            }
            .addOnFailureListener {
                val errorMsg = it.message ?: "Login failed (unknown error)"
                submitState(UiState.Error(errorMsg))
            }
    }
}