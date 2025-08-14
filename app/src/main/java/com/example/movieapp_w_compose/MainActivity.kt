package com.example.movieapp_w_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.movieapp_w_compose.features.bottomNav.TabNavigator
import com.example.movieapp_w_compose.features.presentation.language.components.LanguagePrefs
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.CustomTheme
import com.example.movieapp_w_compose.util.LocalHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val lang = LanguagePrefs.get(this, default = "en")
        LocalHelper.setLocale(this, lang)
        setContent {
            CustomTheme  {
                TabNavigator()
            }
        }
    }
}

