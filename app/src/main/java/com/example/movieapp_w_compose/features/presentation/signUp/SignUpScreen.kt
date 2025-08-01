package com.example.movieapp_w_compose.features.presentation.signUp

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.components.MainButton
import com.example.movieapp_w_compose.features.presentation.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun SignUpScreen(
    backStack: MutableList<MovieDestination>,
    viewModel: SignUpViewModel = viewModel()
) {

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
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            labelText = stringResource(R.string.username),
            leadingIconRes = R.drawable.ic_username
        )
        Spacer(modifier = Modifier.height(10.dp))
        MainTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            labelText = stringResource(R.string.email_address),
            leadingIconRes = R.drawable.ic_gmail
        )
        Spacer(modifier = Modifier.height(10.dp))
        MainTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier.fillMaxWidth(),
            labelText = stringResource(R.string.phone_number),
            leadingIconRes = R.drawable.ic_phone,
            keyboardType = KeyboardType.Phone
        )
        Spacer(modifier = Modifier.height(10.dp))
        MainTextField(
            value = password,
            onValueChange = { password = it },
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
            onClick = {viewModel.handleAction(SignUpUiAction.SignUpClick) },
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

