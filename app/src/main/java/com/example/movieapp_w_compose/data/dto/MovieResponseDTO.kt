package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.MovieResponseEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class MovieResponseDTO(
    @SerializedName("results")
    val results: List<MovieDTO?>
)


fun MovieResponseDTO.toMovieResponseEntity() = MovieResponseEntity(
    results = results.mapNotNull { dtoItem ->
        dtoItem?.toMovieEntity()
    }
)