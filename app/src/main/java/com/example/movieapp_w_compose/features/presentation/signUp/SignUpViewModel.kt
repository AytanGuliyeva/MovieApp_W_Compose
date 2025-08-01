package com.example.movieapp_w_compose.features.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel:ViewModel() {

    private val _eventFlow = MutableSharedFlow<SignUpSingleEvent>()
    val eventFlow = _eventFlow

    init {
        handleAction(SignUpUiAction.Load)
    }

    fun handleAction(action: SignUpUiAction){
        when(action){
            is SignUpUiAction.BackToSignInClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        SignUpSingleEvent.OpenSignInScreen
                    )
                }
            }
            is SignUpUiAction.Load -> {
            }
            is SignUpUiAction.SignUpClick -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        SignUpSingleEvent.OpenHomeScreen
                    )
                }
            }
        }
    }
}