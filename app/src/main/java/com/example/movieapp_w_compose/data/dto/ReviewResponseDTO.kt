package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.ReviewResponseEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class ReviewResponseDTO(
    @SerializedName("results")
    val results: List<ReviewDTO?>
)

fun ReviewResponseDTO.toReviewResponseEntity() = ReviewResponseEntity(
    results = results.mapNotNull { dtoItem ->
        dtoItem?.toReviewEntity()
    }
)