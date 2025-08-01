package com.example.movieapp_w_compose.features.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_w_compose.features.presentation.signUp.SignUpUiAction
import com.example.movieapp_w_compose.state.MviViewModel
import com.example.movieapp_w_compose.state.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel:MviViewModel<
        Unit,
        UiState<Unit>,
        SignInUiAction,
        SignInSingleEvent
        >() {

    init {
        handleAction(SignInUiAction.Load)
    }

    override fun initState(): UiState<Unit>  = UiState.Loading

    override fun handleAction(action: SignInUiAction) {
        when(action){
            is SignInUiAction.Load -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
            }
            is SignInUiAction.CreateOneClick -> {
                submitSingleEvent(SignInSingleEvent.OpenSignUpScreen)
            }
            is SignInUiAction.SignInClick -> {
                submitState(UiState.Loading)
                submitState(UiState.Success(Unit))
                submitSingleEvent(SignInSingleEvent.OpenHomeScreen)
            }
        }
    }
}