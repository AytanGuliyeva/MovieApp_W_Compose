package com.example.movieapp_w_compose.features.presentation.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.data.ConstValues
import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.data.local.PicturesDao
import com.example.movieapp_w_compose.features.presentation.profile.state.ProfileSingleEvent
import com.example.movieapp_w_compose.features.presentation.profile.state.ProfileState
import com.example.movieapp_w_compose.features.presentation.profile.state.ProfileUiAction
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    private val picturesDao: PicturesDao
) : MviViewModel<
        ProfileState,
        UiState<ProfileState>,
        ProfileUiAction,
        ProfileSingleEvent>() {

    override fun initState(): UiState<ProfileState> = UiState.Loading

    override fun handleAction(action: ProfileUiAction) {
        when (action) {
            is ProfileUiAction.Load -> {
                fetchUserInformation()
            }

            is ProfileUiAction.LoadUser -> {
                submitState(UiState.Success(ProfileState(user = action.user)))
            }

            is ProfileUiAction.ShowLogoutDialog -> {
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: return
                val newState = currentState.copy(isLogoutDialogVisible = action.show)
                submitState(UiState.Success(newState))
            }

            is ProfileUiAction.Logout -> {
                auth.signOut()
                submitState(UiState.Success(ProfileState()))
                submitSingleEvent(ProfileSingleEvent.NavigateToLogin)
            }

            is ProfileUiAction.EditProfileClick -> {
                submitSingleEvent(ProfileSingleEvent.NavigateToEditProfile)
            }

            is ProfileUiAction.ChangePasswordClick -> {
                submitSingleEvent(ProfileSingleEvent.NavigateToChangePassword)
            }

            is ProfileUiAction.ChangeLanguage -> {
                val currentState = (uiStateFlow.value as? UiState.Success)?.data ?: return
                val newState = currentState.copy(languageOption = action.language)
                submitState(UiState.Success(newState))
                viewModelScope.launch {
                    kotlinx.coroutines.delay(1000L)
                    submitSingleEvent(ProfileSingleEvent.NavigateToSplash(action.language))
                }

            }
        }
    }

    private fun fetchUserInformation() {
        firestore.collection(ConstValues.USERS).document(auth.currentUser!!.uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toUser()
                    if (user != null) {
                        viewModelScope.launch {
                            val local = picturesDao.getUserPicture(auth.currentUser!!.uid)
                            val localUri = local?.pictureUri.orEmpty()

                            submitState(
                                UiState.Success(
                                    ProfileState(
                                        user = user,
                                        localImageUri = localUri
                                    )
                                )
                            )
                        }
                    }else {
                        submitState(UiState.Error(" User data is null"))
                    }
                } else {
                    submitState(UiState.Error("User document does not exist"))
                }
            }
            .addOnFailureListener { exception ->
                submitState(UiState.Error(exception.message ?: "Unknown error"))

            }
    }

    private fun DocumentSnapshot.toUser(): User? {
        return try {
            val userId = getString(ConstValues.USER_ID)
            val username = getString(ConstValues.USERNAME)
            val email = getString(ConstValues.EMAIL)
            val password = getString(ConstValues.PASSWORD)
            val imageUrl = getString(ConstValues.IMAGE_URL)

            User(
                userId.orEmpty(),
                username.orEmpty(),
                email.orEmpty(),
                password.orEmpty(),
                imageUrl.orEmpty(),
            )
        } catch (e: Exception) {
            null
        }
    }
}