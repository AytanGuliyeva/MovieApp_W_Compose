package com.example.movieapp_w_compose.features.presentation.signUp.viewModel

import com.example.movieapp_w_compose.data.ConstValues
import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.features.presentation.signUp.state.SignUpSingleEvent
import com.example.movieapp_w_compose.features.presentation.signUp.state.SignUpState
import com.example.movieapp_w_compose.features.presentation.signUp.state.SignUpUiAction
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : MviViewModel<
        SignUpState,
        UiState<SignUpState>,
        SignUpUiAction,
        SignUpSingleEvent
        >() {

    private var latestUserInput: User? = null

    override fun initState(): UiState<SignUpState> = UiState.Success(SignUpState())

    override fun handleAction(action: SignUpUiAction) {
        val current = (uiStateFlow.value as? UiState.Success)?.data ?: SignUpState()

        when (action) {

            is SignUpUiAction.BackToSignInClick -> {
                submitSingleEvent(SignUpSingleEvent.OpenSignInScreen)
            }

            is SignUpUiAction.SignUpClick -> {
                latestUserInput = action.input
                performSignUp(action.input)
            }
            is SignUpUiAction.UsernameChanged -> {
                submitState(UiState.Success(current.copy(username = action.value)))
            }

            is SignUpUiAction.EmailChanged -> {
                submitState(UiState.Success(current.copy(email = action.value)))
            }

            is SignUpUiAction.PhoneChanged -> {
                submitState(UiState.Success(current.copy(phone = action.value)))
            }

            is SignUpUiAction.PasswordChanged -> {
                submitState(UiState.Success(current.copy(password = action.value)))
            }

        }
    }

    private fun performSignUp(input: User) {
        if (input.username.isBlank() || input.email.isBlank() || input.password.isBlank() || input.phone.isBlank()) {
            submitState(UiState.Error("Please fill in all fields"))
            return
        }

        if (input.password.length < 6) {
            submitState(UiState.Error("Password must be at least 6 characters"))
            return
        }
        submitState(UiState.Loading)
        auth.createUserWithEmailAndPassword(input.email.trim(), input.password)
            .addOnSuccessListener {
                val userId = auth.currentUser?.uid ?: return@addOnSuccessListener

                val userMap = mapOf(
                    ConstValues.USER_ID to userId,
                    ConstValues.USERNAME to input.username,
                    ConstValues.EMAIL to input.email.trim(),
                    ConstValues.PASSWORD to input.password,
                    ConstValues.PHONE to input.phone

                )
                firestore.collection(ConstValues.USERS).document(userId).set(userMap)
                    .addOnSuccessListener {
                        submitSingleEvent(SignUpSingleEvent.OpenHomeScreen)
                    }
                    .addOnFailureListener {
                        submitState(UiState.Error(it.message ?: "Firestore save failed"))
                    }
            }.addOnFailureListener {
                submitState(UiState.Error(it.message ?: "Firebase sign-up failed"))
            }
    }
}