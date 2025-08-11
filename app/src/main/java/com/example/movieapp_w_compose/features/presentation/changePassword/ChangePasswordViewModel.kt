package com.example.movieapp_w_compose.features.presentation.changePassword

import com.example.movieapp_w_compose.base.ConstValues
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import javax.annotation.meta.When

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
) : MviViewModel<ChangePasswordState,
        UiState<ChangePasswordState>,
        ChangePasswordUiAction,
        ChangePasswordSingleEvent>() {

    override fun initState(): UiState<ChangePasswordState> =
        UiState.Success(ChangePasswordState())

    override fun handleAction(action: ChangePasswordUiAction) {
        val current = (uiStateFlow.value as UiState.Success).data
        when (action) {
            ChangePasswordUiAction.Load -> loadOldPassword()

            ChangePasswordUiAction.BackToProfileClick -> {
                submitSingleEvent(ChangePasswordSingleEvent.OpenProfileScreen)
            }

            is ChangePasswordUiAction.CurrentChanged -> {
                updateState(
                    current.copy(
                        currentPassword = action.value,
                        currentPasswordError = null
                    )
                )
            }

            is ChangePasswordUiAction.NewChanged -> {
                updateState(current.copy(newPassword = action.value, newPasswordError = null))
            }

            is ChangePasswordUiAction.ConfirmChanged -> {
                updateState(
                    current.copy(
                        confirmPassword = action.value,
                        confirmPasswordError = null
                    )
                )
            }

            ChangePasswordUiAction.DoneClick -> submit(current)
        }
    }

    private fun updateState(password: ChangePasswordState) {
        val validated = validate(password)
        submitState(UiState.Success(validated))
    }

    private fun validate(password: ChangePasswordState): ChangePasswordState {

        val newError = when {
            password.newPassword.length < 6 -> "At least 6 characters"
            password.newPassword == password.currentPassword && password.newPassword.isNotEmpty() -> "New must differ"
            else -> null

        }
        val confirmError =
            if (password.confirmPassword.isNotEmpty() && password.confirmPassword != password.newPassword)
                "Passwords do not match" else null

        val can = password.currentPassword.isNotBlank() &&
                password.newPassword.isNotBlank() &&
                password.confirmPassword.isNotBlank() &&
                newError == null && confirmError == null &&
                !password.isLoading

        return password.copy(
            newPasswordError = newError,
            confirmPasswordError = confirmError,
            canSubmit = can
        )
    }

    private fun loadOldPassword() {
        val uid = auth.currentUser?.uid ?: run {
            submitState(UiState.Error("Not authenticated"))
            return
        }
        val current = (uiStateFlow.value as? UiState.Success)?.data ?: ChangePasswordState()
        submitState(UiState.Success(current.copy(isLoading = true)))

        firestore.collection(ConstValues.USERS).document(uid).get()
            .addOnSuccessListener { doc ->
                val old = doc.getString(ConstValues.PASSWORD).orEmpty()
                submitState(
                    UiState.Success(
                        current.copy(
                            oldPasswordFromServer = old,
                            isLoading = false
                        )
                    )
                )
            }
            .addOnFailureListener { e ->
                submitState(UiState.Error(e.message ?: "Failed to load"))
            }
    }


    private fun submit(state: ChangePasswordState) {
        if (state.currentPassword != state.oldPasswordFromServer) {
            submitState(UiState.Success(state.copy(currentPasswordError = "Current password is wrong")))
            submitSingleEvent(ChangePasswordSingleEvent.ShowMessage("Current password is wrong"))
            return
        }

        val v = validate(state)
        if (!v.canSubmit) {
            submitState(UiState.Success(v))
            return
        }

        val user = auth.currentUser ?: run {
            submitState(UiState.Error("Not authenticated"))
            return
        }
        val email = user.email ?: run {
            submitState(UiState.Error("Email not available"))
            return
        }

        submitState(UiState.Success(v.copy(isLoading = true, canSubmit = false)))

        val cred = EmailAuthProvider.getCredential(email, state.currentPassword)
        user.reauthenticate(cred)
            .addOnSuccessListener {
                user.updatePassword(state.newPassword)
                    .addOnSuccessListener {
                        firestore.collection(ConstValues.USERS).document(user.uid)
                            .update(mapOf(ConstValues.PASSWORD to state.newPassword))
                            .addOnSuccessListener {
                                val done = state.copy(
                                    isLoading = false,
                                    isPasswordChanged = true,
                                    currentPassword = "",
                                    newPassword = "",
                                    confirmPassword = "",
                                    currentPasswordError = null,
                                    newPasswordError = null,
                                    confirmPasswordError = null,
                                    canSubmit = false
                                )
                                submitState(UiState.Success(done))
                                submitSingleEvent(ChangePasswordSingleEvent.ShowMessage("Password updated"))
                                submitSingleEvent(ChangePasswordSingleEvent.OpenProfileScreen)
                            }
                            .addOnFailureListener { e ->
                                submitState(UiState.Success(v.copy(isLoading = false)))
                                submitSingleEvent(
                                    ChangePasswordSingleEvent.ShowMessage(
                                        e.message ?: "Update failed (db)"
                                    )
                                )
                            }
                    }
                    .addOnFailureListener { e ->
                        submitState(UiState.Success(v.copy(isLoading = false)))
                        submitSingleEvent(
                            ChangePasswordSingleEvent.ShowMessage(
                                e.message ?: "Update failed"
                            )
                        )
                    }
            }
            .addOnFailureListener { e ->
                submitState(UiState.Success(v.copy(isLoading = false)))
                submitSingleEvent(
                    ChangePasswordSingleEvent.ShowMessage(
                        e.message ?: "Re-auth failed"
                    )
                )
            }
    }

}