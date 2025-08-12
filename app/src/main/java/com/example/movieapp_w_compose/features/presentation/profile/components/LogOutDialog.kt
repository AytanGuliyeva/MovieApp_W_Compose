package com.example.movieapp_w_compose.features.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.log_out), style = MovieAppTheme.typograph.titleMedium, color = MovieAppTheme.colors.red)
        },
        text = {
            Text(text = stringResource(R.string.are_you_sure_you_want_to_log_out), color = MovieAppTheme.colors.white)
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.log_out),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onConfirm() },
                color = MovieAppTheme.colors.red
            )
        },
        dismissButton = {
            Text(
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDismiss() }
                ,color = MovieAppTheme.colors.white
            )
        },
        containerColor = MovieAppTheme.colors.menuColor
    )
}
