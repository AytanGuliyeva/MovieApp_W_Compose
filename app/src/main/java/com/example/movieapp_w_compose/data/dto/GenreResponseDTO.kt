package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.GenreResponseEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class GenreResponseDTO(
    @SerializedName("genres")
    val genres: List<GenreDTO?>
)

fun GenreResponseDTO.toGenreResponseEntity() = GenreResponseEntity(
    genres = genres.mapNotNull { dtoItem ->
        dtoItem?.toGenreEntity()
    }
)