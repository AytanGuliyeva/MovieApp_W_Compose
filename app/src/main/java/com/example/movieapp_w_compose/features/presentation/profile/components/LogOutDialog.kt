package com.example.movieapp_w_compose.features.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Log Out", style = MovieAppTheme.typograph.titleMedium, color = MovieAppTheme.colors.red)
        },
        text = {
            Text(text = "Are you sure you want to log out?", color = MovieAppTheme.colors.white)
        },
        confirmButton = {
            Text(
                text = "Log Out",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onConfirm() },
                color = MovieAppTheme.colors.red
            )
        },
        dismissButton = {
            Text(
                text = "Cancel",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDismiss() }
                ,color = MovieAppTheme.colors.white
            )
        },
        containerColor = MovieAppTheme.colors.menuColor
    )
}
