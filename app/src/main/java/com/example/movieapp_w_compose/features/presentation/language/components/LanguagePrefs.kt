package com.example.movieapp_w_compose.features.presentation.language.components

import android.content.Context
import android.util.Log
import androidx.core.content.edit

object LanguagePrefs {
    private const val PREF_NAME= "app_prefs"
    private const val KEY_LANG =  "app_language"

    fun save(context: Context,langCode:String){
        context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            .edit { putString(KEY_LANG, langCode) }
        Log.e("lang", "onCreate: $langCode", )

    }
    fun get(context: Context, default: String = "en"): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_LANG, default) ?: default
    }

}