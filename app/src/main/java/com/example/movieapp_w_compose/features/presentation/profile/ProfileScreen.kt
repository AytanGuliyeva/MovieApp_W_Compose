package com.example.movieapp_w_compose.features.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.profile.components.LogoutDialog
import com.example.movieapp_w_compose.features.presentation.profile.components.ProfileItem
import com.example.movieapp_w_compose.features.presentation.signIn.SignInUiAction
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen

@Composable
fun ProfileScreen(
    backStack: MutableList<MovieDestination>,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.uiStateFlow.collectAsState()
    val singleEvent by viewModel.singleEventFlow.collectAsState(null)


    LaunchedEffect(singleEvent) {
        when (singleEvent) {
            ProfileSingleEvent.NavigateToLogin -> {
                onLogout()
            }
            ProfileSingleEvent.NavigateToEditProfile -> {
                backStack.add(MovieDestination.EditProfile)
            }
            ProfileSingleEvent.NavigateToChangePassword -> {
                backStack.add(MovieDestination.ChangePassword)
            }
            null -> Unit
        }
    }



    CommonScreen(state = state) { profileState ->

        if (profileState.isLogoutDialogVisible) {
            LogoutDialog(
                onConfirm = {
                    viewModel.submitAction(ProfileUiAction.Logout)
                },
                onDismiss = {
                    viewModel.submitAction(ProfileUiAction.ShowLogoutDialog(false))
                }
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieAppTheme.colors.mainColor)
                .verticalScroll(
                    rememberScrollState()
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))
            Box {
                Image(
                    painter = painterResource(id = R.drawable.profile_photo_default),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            2.dp, MovieAppTheme.colors.red,
                            CircleShape
                        )
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .background(Color.Transparent)
                        .clickable {
                            viewModel.handleAction(ProfileUiAction.EditProfileClick)
                        },
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            profileState.user?.let {
                Text(
                    text = "Hi, ${it.username}",
                    style = MovieAppTheme.typograph.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            ProfileItem(
                iconRes = R.drawable.ic_edit,
                text = stringResource(R.string.edit_profile),
                onClick = {viewModel.handleAction(ProfileUiAction.EditProfileClick)
                })
            ProfileItem(
                iconRes = R.drawable.ic_passwords,
                text = stringResource(R.string.change_password),
                onClick = {
                    backStack.add(MovieDestination.ChangePassword)
                    viewModel.handleAction(ProfileUiAction.ChangePasswordClick)
                })
            ProfileItem(
                iconRes = R.drawable.ic_language,
                text = stringResource(R.string.language),
                onClick = { })

            Spacer(modifier = Modifier.height(50.dp))


            Box(
                modifier = Modifier
                    .clickable {
                        viewModel.submitAction(ProfileUiAction.ShowLogoutDialog(true))
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = stringResource(R.string.log_out),
                        tint = MovieAppTheme.colors.red,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = stringResource(R.string.log_out),
                        style = MovieAppTheme.typograph.titleMedium,
                        color = MovieAppTheme.colors.red
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {

}