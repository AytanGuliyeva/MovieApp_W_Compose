package com.example.movieapp_w_compose.features.presentation.language

import androidx.annotation.StringRes
import com.example.movieapp_w_compose.R

enum class Languages(val tag: String, @StringRes val label: Int) {
    AZ("az", R.string.az),
    EN("en", R.string.eng)
}