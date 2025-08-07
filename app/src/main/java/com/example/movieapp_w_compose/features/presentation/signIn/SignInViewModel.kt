package com.example.movieapp_w_compose.features.presentation.signIn

import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val auth: FirebaseAuth) : MviViewModel<
        SignInState,
        UiState<SignInState>,
        SignInUiAction,
        SignInSingleEvent
        >() {

    init {
        handleAction(SignInUiAction.Load)
    }

    override fun initState(): UiState<SignInState> = UiState.Loading

    override fun handleAction(action: SignInUiAction) {
        val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: SignInState()

        when (action) {
            is SignInUiAction.Load -> {
                submitState(UiState.Loading)
                submitState(
                    UiState.Success(
                        currentState.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    )
                )

        }
            is SignInUiAction.CreateOneClick -> {
                submitSingleEvent(SignInSingleEvent.OpenSignUpScreen)
            }

            is SignInUiAction.SignInClick -> {
                signWithEmail(action.email, action.password)
            }


            is SignInUiAction.EmailChanged -> {
                submitState(UiState.Success(currentState.copy(email = action.value)))
            }
            is SignInUiAction.PasswordChanged -> {
                submitState(UiState.Success(currentState.copy(password = action.value)))
            }
            is SignInUiAction.ClearForm -> {
                submitState(UiState.Success(SignInState()))
            }


        }
    }

    private fun signWithEmail(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            submitState(
                UiState.Success(
                    SignInState(
                        email,
                        password,
                        errorMessage = "Email və şifrə boş ola bilməz"
                    )
                )
            )
            return
        }

        submitState(UiState.Success(SignInState(email, password, isLoading = true)))

        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnSuccessListener {
                submitSingleEvent(SignInSingleEvent.OpenHomeScreen)

            }
            .addOnFailureListener {
                submitState(
                    UiState.Success(
                        SignInState(
                            email = email,
                            password = password,
                            errorMessage = it.message ?: "Login failed (unknown error)"
                        )
                    )
                )
            }

    }
}