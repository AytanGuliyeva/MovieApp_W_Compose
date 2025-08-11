package com.example.movieapp_w_compose.features.presentation.changePassword

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.home.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(
    backStack: MutableList<MovieDestination>,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val state by viewModel.uiStateFlow.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.handleAction(ChangePasswordUiAction.Load)
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is ChangePasswordSingleEvent.OpenProfileScreen -> {
                    backStack.removeLastOrNull()
                }

                is ChangePasswordSingleEvent.ShowMessage -> {
                    Toast.makeText(context, event.msg, Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MovieAppTheme.colors.mainColor,
                        navigationIconContentColor = MovieAppTheme.colors.red,
                        titleContentColor = MovieAppTheme.colors.white,
                        actionIconContentColor = MovieAppTheme.colors.red
                    ),
                    navigationIcon = {
                        IconButton(onClick = { viewModel.handleAction(ChangePasswordUiAction.BackToProfileClick) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = stringResource(R.string.back),
                                tint = MovieAppTheme.colors.red
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(R.string.change_password),
                            style = MovieAppTheme.typograph.calloutMedium
                        )
                    },
                    actions = {
                        Text(
                            text = stringResource(R.string.done),
                            style = MovieAppTheme.typograph.subtitleMedium,
                            color = MovieAppTheme.colors.red,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickable {viewModel.handleAction(ChangePasswordUiAction.DoneClick) }
                        )
                    }
                )
                Divider(color = colorResource(id = R.color.grey))
            }
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { inner ->

        CommonScreen(state = state) { state ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieAppTheme.colors.mainColor)
                    .padding(inner),

                ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(R.string.alert_change_password),
                    style = MovieAppTheme.typograph.footnoteRegular
                )

                Spacer(modifier = Modifier.height(30.dp))

                MainTextField(
                    value = state.currentPassword,
                    onValueChange = {
                        viewModel.handleAction(
                            ChangePasswordUiAction.CurrentChanged(
                                it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    labelText = stringResource(R.string.current_password),
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                MainTextField(
                    value = state.newPassword,
                    onValueChange = {
                        viewModel.handleAction(ChangePasswordUiAction.NewChanged(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    labelText = stringResource(R.string.new_password),
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                MainTextField(
                    value = state.confirmPassword,
                    onValueChange = {
                        viewModel.handleAction(
                            ChangePasswordUiAction.ConfirmChanged(
                                it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    labelText = stringResource(R.string.re_type_new_password),
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MovieAppTheme.colors.mainColor.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {

}