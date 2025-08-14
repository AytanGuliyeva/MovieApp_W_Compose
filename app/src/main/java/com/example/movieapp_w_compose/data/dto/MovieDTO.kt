package com.example.movieapp_w_compose.data.dto

import androidx.compose.runtime.Immutable
import com.example.movieapp_w_compose.data.domain.MovieEntity
import com.google.gson.annotations.SerializedName

@Immutable
data class MovieDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
)
fun MovieDTO.toMovieEntity() =  MovieEntity(
    id = id ?: 0,
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty()
)