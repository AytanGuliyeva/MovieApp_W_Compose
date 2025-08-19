package com.example.movieapp_w_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movieapp_w_compose.features.bottomNav.TabNavigator
import com.example.movieapp_w_compose.features.presentation.language.components.LanguagePrefs
import com.example.movieapp_w_compose.features.presentation.splash.SplashScreenViewModel
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.CustomTheme
import com.example.movieapp_w_compose.util.LocalHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        //   splash.setKeepOnScreenCondition { true }
        //   enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(Color.RED, Color.RED))
        val lang = LanguagePrefs.get(this, default = "en")
        LocalHelper.setLocale(this, lang)
        setContent {
            CustomTheme  {
              //  splash.setKeepOnScreenCondition { false }
                TabNavigator()
            }
        }
    }
}

