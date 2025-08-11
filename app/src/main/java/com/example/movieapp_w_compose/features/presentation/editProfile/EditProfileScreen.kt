package com.example.movieapp_w_compose.features.presentation.editProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.changePassword.ChangePasswordSingleEvent
import com.example.movieapp_w_compose.features.presentation.home.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    backStack : MutableList<MovieDestination>,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is EditProfileSingleEvent.OpenProfileScreen -> {
                    backStack.removeLastOrNull()
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
                        actionIconContentColor = MovieAppTheme.colors.red),
                                navigationIcon = {
                        IconButton(onClick = {
                            viewModel.handleAction(EditProfileUiAction.BackToProfile)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = stringResource(R.string.back),
                                tint = MovieAppTheme.colors.red
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(R.string.edit_profile),
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
                                .clickable { }
                        )
                    }
                )
                Divider(color = colorResource(id = R.color.grey))
            }
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .padding(inner)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_photo_default),
                        contentDescription = "Profile Image",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Text(
                text = "Edit Picture",
                style = MovieAppTheme.typograph.subtitleMedium,
                color = MovieAppTheme.colors.red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Username",
                    style = MovieAppTheme.typograph.subtitleMedium,
                    modifier = Modifier.padding(end = 10.dp)
                )
                MainTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth(),
                    labelText = "Username",
                    leadingIconRes = R.drawable.ic_username,
                    isPassword = false
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
}