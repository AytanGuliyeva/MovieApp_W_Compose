package com.example.movieapp_w_compose.features.presentation.signUp.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.data.User
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.components.MainButton
import com.example.movieapp_w_compose.features.presentation.home.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.signUp.state.SignUpSingleEvent
import com.example.movieapp_w_compose.features.presentation.signUp.state.SignUpUiAction
import com.example.movieapp_w_compose.features.presentation.signUp.viewModel.SignUpViewModel
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen
import com.example.movieapp_w_compose.state.UiState.*

@Composable
fun SignUpScreen(
    backStack: MutableList<MovieDestination>,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiStateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is SignUpSingleEvent.OpenSignInScreen ->
                    backStack.removeLastOrNull()

                is SignUpSingleEvent.OpenHomeScreen -> {
                    backStack.clear()
                    backStack.add(MovieDestination.Home)
                }
            }
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is Error) {
            val message = (uiState as Error).errorMessage
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    CommonScreen(state = uiState) { signUpScreen ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.create_an_account),
                style = MovieAppTheme.typograph.headlineMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.please_fill_in_your_accurate_information),
                style = MovieAppTheme.typograph.calloutMedium
            )
            Spacer(modifier = Modifier.height(30.dp))
            MainTextField(
                value = signUpScreen.username,
                onValueChange = {viewModel.handleAction(SignUpUiAction.UsernameChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.username),
                leadingIconRes = R.drawable.ic_username
            )
            Spacer(modifier = Modifier.height(10.dp))
            MainTextField(
                value = signUpScreen.email,
                onValueChange = {viewModel.handleAction(SignUpUiAction.EmailChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.email_address),
                leadingIconRes = R.drawable.ic_gmail,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(10.dp))
            MainTextField(
                value = signUpScreen.phone,
                onValueChange = {viewModel.handleAction(SignUpUiAction.PhoneChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.phone_number),
                leadingIconRes = R.drawable.ic_phone,
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(10.dp))
            MainTextField(
                value = signUpScreen.password,
                onValueChange = {viewModel.handleAction(SignUpUiAction.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.password),
                leadingIconRes = R.drawable.ic_lock,
                isPassword = true

            )
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    viewModel.handleAction(
                        SignUpUiAction.SignUpClick(
                            User(
                                username = signUpScreen.username,
                                email = signUpScreen.email,
                                password = signUpScreen.password,
                                phone = signUpScreen.phone
                            )
                        )
                    )
                },
                text = stringResource(R.string.sign_up)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .semantics(mergeDescendants = true) { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.you_already_have_an_account), color = Color.White
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.clickable { viewModel.handleAction(SignUpUiAction.BackToSignInClick) },
                    text = stringResource(R.string.sign_in), color = MovieAppTheme.colors.red
                )
            }
            Spacer(modifier = Modifier.height(10.dp))


            Icon(
                painter = painterResource(R.drawable.ic_back_logo),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { viewModel.handleAction(SignUpUiAction.BackToSignInClick) }
                    .padding(4.dp),
                tint = Color.Unspecified
            )
        }
    }
}

