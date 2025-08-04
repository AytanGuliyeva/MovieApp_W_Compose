package com.example.movieapp_w_compose.features.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp_w_compose.base.ConstValues
import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : MviViewModel<
        Unit,
        UiState<Unit>,
        SignUpUiAction,
        SignUpSingleEvent
        >() {

    private var latestUserInput: User? = null


    override fun initState(): UiState<Unit> = UiState.Loading

    override fun handleAction(action: SignUpUiAction) {
        when (action) {
            is SignUpUiAction.Load -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
            }

            is SignUpUiAction.BackToSignInClick -> {
                submitSingleEvent(SignUpSingleEvent.OpenSignInScreen)
            }

            is SignUpUiAction.SignUpClick -> {
                latestUserInput = action.input
                performSignUp(action.input)
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