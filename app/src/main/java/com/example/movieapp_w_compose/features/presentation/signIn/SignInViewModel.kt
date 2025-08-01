package com.example.movieapp_w_compose.features.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel:ViewModel() {

    private val _eventFlow = MutableSharedFlow<SignInSingleEvent>()
    val eventFlow = _eventFlow

    init {
        handleAction(SignInUiAction.Load)
    }

     fun handleAction(action: SignInUiAction){
         when(action){
             is SignInUiAction.CreateOneClick -> {
                 viewModelScope.launch {
                     _eventFlow.emit(
                         SignInSingleEvent.OpenSignUpScreen
                     )
                 }
             }
             is SignInUiAction.Load -> {
             }
             is SignInUiAction.SignInClick ->{
                 viewModelScope.launch {
                     _eventFlow.emit(
                         SignInSingleEvent.OpenHomeScreen
                     )
                 }
             }
         }
    }
}