package com.example.movieapp_w_compose.features.presentation.SignIn

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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.presentation.components.MainButton
import com.example.movieapp_w_compose.features.presentation.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            labelText = stringResource(R.string.email_address),
            leadingIconRes =R.drawable.ic_gmail
            )
       /* OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email_address)) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                focusedIndicatorColor = Color.White,
                cursorColor = Color.White
            ), leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_gmail),
                    contentDescription = "gmail",
                    tint = Color.Unspecified
                )
            },
            modifier = Modifier.fillMaxWidth()
        )*/
        Spacer(modifier = Modifier.height(10.dp))
        MainTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            labelText = stringResource(R.string.password),
            leadingIconRes =R.drawable.ic_hide,
            isPassword = true
        )
        /*OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color.White,
                focusedIndicatorColor = Color.White,
                cursorColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_hide),
                    contentDescription = "gmail",
                    tint = Color.Unspecified
                )
            },
            modifier = Modifier.fillMaxWidth()
        )*/
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
            onClick = { },
            text = stringResource(R.string.sign_in)
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.don_t_you_have_an_account), color = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.clickable { },
                text = stringResource(R.string.create_one), color = MovieAppTheme.colors.red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SignInScreen()
}