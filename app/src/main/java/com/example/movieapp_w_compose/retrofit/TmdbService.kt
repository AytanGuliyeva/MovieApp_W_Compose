package com.example.movieapp_w_compose.retrofit

import com.example.movieapp_w_compose.data.dto.GenreResponseDTO
import com.example.movieapp_w_compose.data.dto.MovieDTO
import com.example.movieapp_w_compose.data.dto.MovieResponseDTO
import com.example.movieapp_w_compose.data.dto.ReviewResponseDTO
import com.example.movieapp_w_compose.data.dto.VideoResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
    ): Response<MovieResponseDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    ): Response<MovieResponseDTO>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
    ): Response<MovieResponseDTO>

    @GET("discover/movie")
    suspend fun getCategoryMovies(
        @Query("with_genres") genreId: Int?
    ): Response<MovieResponseDTO>


    @GET("discover/movie")
    suspend fun getMovies(
    ): Response<MovieResponseDTO>


    @GET("genre/movie/list")
    suspend fun getGenres(
    ): Response<GenreResponseDTO>


    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<MovieResponseDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
    ): Response<VideoResponseDTO>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: Int,
    ): Response<MovieDTO>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
    ): Response<ReviewResponseDTO>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int
    ): Response<MovieResponseDTO>

}