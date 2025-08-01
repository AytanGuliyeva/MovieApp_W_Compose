package com.example.movieapp_w_compose.features.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : MviViewModel<
        Unit,
        UiState<Unit>,
        SignUpUiAction,
        SignUpSingleEvent
        >() {

    override fun initState(): UiState<Unit>  = UiState.Loading

    override fun handleAction(action: SignUpUiAction) {
        when(action){
            is SignUpUiAction.Load -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
            }
            is SignUpUiAction.BackToSignInClick -> {
                submitSingleEvent(SignUpSingleEvent.OpenSignInScreen)
            }
            is SignUpUiAction.SignUpClick -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
                submitSingleEvent(SignUpSingleEvent.OpenHomeScreen)
            }

        }
    }
}