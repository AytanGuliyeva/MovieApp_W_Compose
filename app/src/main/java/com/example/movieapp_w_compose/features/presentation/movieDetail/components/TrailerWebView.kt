package com.example.movieapp_w_compose.features.presentation.movieDetail.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TrailerWebView(
    url: String,
    modifier: Modifier = Modifier
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val webView = remember {
        android.webkit.WebView(context).apply {
            layoutParams = android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            webChromeClient = android.webkit.WebChromeClient()
        }
    }

    LaunchedEffect(url) {
        webView.loadUrl(url)
    }

    androidx.compose.runtime.DisposableEffect(Unit) {
        onDispose { webView.destroy() }
    }

    AndroidView(
        factory = { webView },
        modifier = modifier
    )
}