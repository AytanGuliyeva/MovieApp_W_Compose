package com.example.movieapp_w_compose.state

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun <T : Any> CommonScreen(state: UiState<T>, onSuccess: @Composable (T) -> Unit) {

    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
    when (state) {
        is UiState.Loading -> {
            Loading()
        }

        is UiState.Success -> {
            onSuccess(state.data)
        }

        is UiState.Error -> {
        }
    }
}

@Composable
fun Error(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}