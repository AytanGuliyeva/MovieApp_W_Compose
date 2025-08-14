package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.domain.ReviewEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class ReviewDTO(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("id")
    val id: String?,

    )

fun ReviewDTO.toReviewEntity() = ReviewEntity(
    author = author.orEmpty(),
    content = content.orEmpty(),
    id = id.orEmpty()
)