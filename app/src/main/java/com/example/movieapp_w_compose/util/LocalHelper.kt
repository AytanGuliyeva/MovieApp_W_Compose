package com.example.movieapp_w_compose.util

import android.content.Context
import java.util.Locale

object LocalHelper {
    fun setLocale(context: Context, language: String) {
        val metrics = context.resources.displayMetrics
        val configuration = context.resources.configuration
        configuration.locale = Locale(language)
        context.resources.updateConfiguration(configuration, metrics)
    }
}
