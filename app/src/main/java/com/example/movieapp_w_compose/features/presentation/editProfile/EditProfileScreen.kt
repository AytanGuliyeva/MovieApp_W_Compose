package com.example.movieapp_w_compose.features.presentation.editProfile

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.changePassword.ChangePasswordSingleEvent
import com.example.movieapp_w_compose.features.presentation.home.components.MainTextField
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme
import com.example.movieapp_w_compose.state.CommonScreen
import com.example.movieapp_w_compose.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    backStack: MutableList<MovieDestination>,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiStateFlow.collectAsState().value
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            viewModel.handleAction(EditProfileUiAction.PictureSelected(it.toString()))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collect { event ->
            when (event) {
                is EditProfileSingleEvent.OpenProfileScreen -> {
                    backStack.removeLastOrNull()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.handleAction(EditProfileUiAction.Load)
    }
    Scaffold(
        topBar = {
            val canSave = (uiState as? UiState.Success)?.data?.canSave == true
            Column {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MovieAppTheme.colors.mainColor,
                        navigationIconContentColor = MovieAppTheme.colors.red,
                        titleContentColor = MovieAppTheme.colors.white,
                        actionIconContentColor = MovieAppTheme.colors.red
                    ),
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
                                .clickable(enabled = canSave) {
                                    viewModel.handleAction(EditProfileUiAction.Save)
                                }
                        )
                    }
                )
                Divider(color = colorResource(id = R.color.grey))
            }
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { inner ->
        CommonScreen(state = uiState) { state ->


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
                        .clickable { launcher.launch(arrayOf("image/*")) }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val imagePainter = if (state.previewImageUrl.isNotEmpty()) {
                            rememberAsyncImagePainter(state.previewImageUrl)
                        } else {
                            painterResource(id = R.drawable.profile_photo_default)
                        }
                        Image(
                            painter = imagePainter,
                            contentDescription = stringResource(R.string.profile_image),
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.edit_picture),
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
                        text = stringResource(R.string.username),
                        style = MovieAppTheme.typograph.subtitleMedium,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    MainTextField(
                        value = state.username,
                        onValueChange = {
                            viewModel.handleAction(
                                EditProfileUiAction.UsernameChanged(
                                    it
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        labelText = stringResource(R.string.username),
                        leadingIconRes = R.drawable.ic_username,
                        isPassword = false
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
}