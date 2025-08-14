package com.example.movieapp_w_compose.features.presentation.language.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.movieapp_w_compose.R
import com.example.movieapp_w_compose.data.Languages
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme


@Composable
fun LanguageDropdown(
    modifier: Modifier = Modifier,
    selectedLanguage: Languages,
    onOptionSelected: (option: Languages) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable(
                    onClick = { expanded = !expanded },
                )
                .padding(20.dp)
        ) {
            Icon(
                modifier = Modifier.padding(start = 5.dp, end = 8.dp),
                painter = painterResource(id = R.drawable.ic_language),
                contentDescription = null,
                tint = MovieAppTheme.colors.red
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.language),
                style = MovieAppTheme.typograph.calloutMedium
            )
            Text(
                text = stringResource(id = selectedLanguage.label),
                style = MovieAppTheme.typograph.subtitleMedium,
                color = MovieAppTheme.colors.red
            )
            DropdownMenu(
                modifier = Modifier.background(MovieAppTheme.colors.menuColor),
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(16.dp, 0.dp)
            ) {
                Languages.values().forEach { lang ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onOptionSelected(lang)
                        }
                    ) {
                        Text(
                            text = stringResource(id = lang.label),
                            color = MovieAppTheme.colors.red
                        )
                    }
                }
            }
        }
    }
}