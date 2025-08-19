package com.example.movieapp_w_compose.features.presentation.signIn.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.components.MainButton
import com.example.movieapp_w_compose.features.presentation.home.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.signIn.viewModel.SignInViewModel
import com.example.movieapp_w_compose.features.presentation.signIn.state.SignInSingleEvent
import com.example.movieapp_w_compose.features.presentation.signIn.state.SignInUiAction
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen
import com.example.movieapp_w_compose.state.UiState
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignInScreen(
    backStack: MutableList<MovieDestination>,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsState()
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        viewModel.submitAction(SignInUiAction.ClearForm)
        if (auth.currentUser != null) {
            backStack.clear()
            backStack.add(MovieDestination.Home)
        } else {
            backStack.clear()
            backStack.add(MovieDestination.SignIn)
        }
    }


    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is SignInSingleEvent.OpenSignUpScreen -> {
                    backStack.add(MovieDestination.SignUp)
                }

                is SignInSingleEvent.OpenHomeScreen -> {
                    backStack.clear()
                    backStack.add(MovieDestination.Home)
                }
            }
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            val message = (uiState as UiState.Error).errorMessage
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    CommonScreen(state = uiState) { signInState ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = stringResource(R.string.sign_in),
                style = MovieAppTheme.typograph.headlineMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.login_to_continue_with_the_app),
                style = MovieAppTheme.typograph.calloutMedium
            )
            Spacer(modifier = Modifier.height(30.dp))
            MainTextField(
                value = signInState.email,
                onValueChange = { viewModel.handleAction(SignInUiAction.EmailChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.email_address),
                leadingIconRes = R.drawable.ic_gmail
            )
            Spacer(modifier = Modifier.height(10.dp))
            MainTextField(
                value = signInState.password,
                onValueChange = { viewModel.handleAction(SignInUiAction.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                labelText = stringResource(R.string.password),
                leadingIconRes = R.drawable.ic_lock,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.End),
                text = stringResource(R.string.forgot_password),
                color = MovieAppTheme.colors.red,
            )
            Spacer(modifier = Modifier.height(15.dp))
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    viewModel.handleAction(
                        SignInUiAction.SignInClick(
                            signInState.email,
                            signInState.password
                        )
                    )
                }, text = stringResource(R.string.sign_in)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.or_login_with),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.clickable { },
                    painter = painterResource(R.drawable.ic_fb),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.clickable { },
                    painter = painterResource(R.drawable.ic_apple),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.clickable { },

                    painter = painterResource(R.drawable.ic_google_plus),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .semantics(mergeDescendants = true) { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.don_t_you_have_an_account), color = Color.White
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    modifier = Modifier.clickable {
                        viewModel.handleAction(SignInUiAction.CreateOneClick)
                    },
                    text = stringResource(R.string.create_one), color = MovieAppTheme.colors.red
                )
            }
        }
    }
}