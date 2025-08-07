package com.example.movieapp_w_compose.retrofit

import com.example.movieapp_w_compose.retrofit.model.GenreResponse
import com.example.movieapp_w_compose.retrofit.model.MovieResponse
import com.example.movieapp_w_compose.retrofit.model.ReviewResponse
import com.example.movieapp_w_compose.retrofit.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") bearerToken: String
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Header("Authorization") bearerToken: String
    ): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") bearerToken: String
    ): Response<MovieResponse>

    @GET("discover/movie")
    suspend fun getCategoryMovies(
        @Header("Authorization") bearerToken: String,
        @Query("with_genres") genreId: Int
    ): Response<MovieResponse>


    @GET("discover/movie")
    suspend fun getMovies(
        @Header("Authorization") bearerToken: String
    ): Response<MovieResponse>


    @GET("genre/movie/list")
    suspend fun getGenres(
        @Header("Authorization") bearerToken: String
    ): Response<GenreResponse>


    @GET("search/movie")
    suspend fun searchMovies(
        @Header("Authorization") bearerToken: String,
        @Query("query") query: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<VideoResponse>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<ReviewResponse>
}