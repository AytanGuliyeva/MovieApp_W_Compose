package com.example.movieapp_w_compose.features.presentation.editProfile

import android.app.ProgressDialog
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.data.local.PicturesDao
import com.example.movieapp_w_compose.data.local.PicturesEntity
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import com.example.movieapp_w_compose.util.ConstValues
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    private val picturesDao: PicturesDao
) : MviViewModel<EditProfileState,
        UiState<EditProfileState>,
        EditProfileUiAction,
        EditProfileSingleEvent>() {
    override fun initState(): UiState<EditProfileState> = UiState.Loading

    override fun handleAction(action: EditProfileUiAction) {
        when (action) {
            EditProfileUiAction.Load -> loadUser()
            EditProfileUiAction.BackToProfile -> submitSingleEvent(EditProfileSingleEvent.OpenProfileScreen)
            is EditProfileUiAction.UsernameChanged -> {
                val current = (uiStateFlow.value as? UiState.Success)?.data ?: return
                val newUsername = action.value
                submitState(
                    UiState.Success(
                        current.copy(
                            username = newUsername,
                            usernameError = null,
                            canSave = newUsername.trim().isNotEmpty() &&
                                    newUsername.trim() != current.initialUsername
                        )
                    )
                )
            }

            EditProfileUiAction.Save -> save()
            is EditProfileUiAction.PictureSelected -> {
                val current = (uiStateFlow.value as? UiState.Success)?.data ?: return
                submitState(
                    UiState.Success(
                        current.copy(
                            previewImageUrl = action.uri,
                            canSave = true
                        )
                    )
                )
            }
        }
    }

    private fun loadUser() {
        submitState(UiState.Loading)
        firestore.collection(ConstValues.USERS).document(auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { doc ->
                val username = doc.getString(ConstValues.USERNAME).orEmpty()
                viewModelScope.launch {
                    val local = picturesDao.getUserPicture(auth.currentUser!!.uid)
                    val localUri = local?.pictureUri.orEmpty()
                    picturesDao.insertOrUpdatePicture(
                        PicturesEntity(
                            userId = auth.currentUser!!.uid,
                            username = username,
                            pictureUri = localUri
                        )
                    )

                    submitState(
                        UiState.Success(
                            EditProfileState(
                                initialUsername = username,
                                username = username,
                                previewImageUrl = localUri,
                                canSave = false,
                                isLoading = false
                            )
                        )
                    )
                }
            }
            .addOnFailureListener { e ->
                submitState(UiState.Error(e.localizedMessage ?: " Unknown error"))
            }
    }

    private fun save() {
        val success = (uiStateFlow.value as? UiState.Success)?.data ?: return
        if (!success.canSave) return

        val uid = auth.currentUser?.uid ?: return
        val newName = success.username.trim()

        submitState(UiState.Success(success.copy(isLoading = true)))

        val hasUsernameChanged = newName != success.initialUsername
        val persistToRoom: suspend () -> Unit = {
            val currentLocal = picturesDao.getUserPicture(uid)
            val finalUri = if (success.previewImageUrl.isNotEmpty())
                success.previewImageUrl
            else
                currentLocal?.pictureUri.orEmpty()

            picturesDao.insertOrUpdatePicture(
                PicturesEntity(
                    userId = uid,
                    username = newName,
                    pictureUri = finalUri
                )
            )
        }
        if (hasUsernameChanged) {
            firestore.collection(ConstValues.USERS).document(uid)
                .update(ConstValues.USERNAME, newName)
                .addOnSuccessListener {
                    viewModelScope.launch {
                        persistToRoom()
                        val updated = success.copy(
                            initialUsername = newName,
                            username = newName,
                            isLoading = false,
                            canSave = false,
                            isSaved = true
                        )
                        submitState(UiState.Success(updated))
                        submitSingleEvent(EditProfileSingleEvent.OpenProfileScreen)
                    }
                }
                .addOnFailureListener { e ->
                    submitState(
                        UiState.Success(
                            success.copy(isLoading = false, usernameError = e.localizedMessage)
                        )
                    )
                }
        } else {
            viewModelScope.launch {
                persistToRoom()
                val updated = success.copy(
                    isLoading = false,
                    canSave = false,
                    isSaved = true
                )
                submitState(UiState.Success(updated))
                submitSingleEvent(EditProfileSingleEvent.OpenProfileScreen)
            }
        }
    }
}
