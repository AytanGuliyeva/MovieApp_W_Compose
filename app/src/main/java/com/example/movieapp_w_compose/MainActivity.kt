package com.example.movieapp_w_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movieapp_w_compose.features.bottomNav.TabNavigator
import com.example.movieapp_w_compose.features.navigation.MovieDestination
import com.example.movieapp_w_compose.features.presentation.language.components.LanguagePrefs
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.CustomTheme
import com.example.movieapp_w_compose.util.LocalHelper
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { true }
        val lang = LanguagePrefs.get(this, default = "en")
        LocalHelper.setLocale(this, lang)

        val auth = FirebaseAuth.getInstance()

        setContent {
            CustomTheme  {
              var firstScreen:MovieDestination= if (auth.currentUser != null) {
                      MovieDestination.Home
                  } else {
                      MovieDestination.SignIn
                  }

                TabNavigator(firstScreen)
                splash.setKeepOnScreenCondition { false }
            }
        }
    }
}

