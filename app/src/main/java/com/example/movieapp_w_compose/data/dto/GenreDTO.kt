package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.domain.model.GenreEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class GenreDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

fun GenreDTO.toGenreEntity() = GenreEntity(
    id = id ?: 0,
    name = name.orEmpty()
)
