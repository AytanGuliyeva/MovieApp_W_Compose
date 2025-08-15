package com.example.movieapp_w_compose.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ReviewEntity(
    val author: String,
    val content: String,
    val id: String
)