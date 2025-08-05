package com.example.movieapp_w_compose.retrofit


import com.example.movieapp_w_compose.retrofit.model.GenreResponse
import com.example.movieapp_w_compose.retrofit.model.MovieResponse
import com.example.movieapp_w_compose.retrofit.model.ReviewResponse
import com.example.movieapp_w_compose.retrofit.model.VideoResponse
import jakarta.inject.Inject
import retrofit2.Response

class Repository @Inject constructor(
    private val tmdbApi: TmdbService
) {

   // private val tmdbApi = RetrofitInstance.getInstance().create(TmdbService::class.java)

    suspend fun getPopularMovies(apiKey: String): Response<MovieResponse> {
        return tmdbApi.getPopularMovies(apiKey)

    }
//    suspend fun getPopularMovies(apiToken: String): Response<MovieResponse> {
//        return tmdbApi.getPopularMovies("Bearer $apiToken")
//    }


    suspend fun getTopRatedMovies(apiKey: String): Response<MovieResponse> {
        return tmdbApi.getTopRatedMovies(apiKey)
    }

    suspend fun getNowPlayingMovies(apiKey: String): Response<MovieResponse> {
        return tmdbApi.getNowPlayingMovies("Bearer $apiKey")
    }

    suspend fun getCategoryMovies(apiKey: String, genreId: Int): Response<MovieResponse> {
        return tmdbApi.getCategoryMovies(apiKey, genreId)
    }
    suspend fun getGenres(apiKey: String): Response<GenreResponse> {
        return tmdbApi.getGenres(apiKey)
    }
    suspend fun getMovies(apiKey: String): Response<MovieResponse> {
        return tmdbApi.getMovies(apiKey)
    }
    suspend fun getSearch(apiKey: String, query: String): Response<MovieResponse> {
        return tmdbApi.searchMovies(apiKey, query)
    }

    suspend fun getVideos(movie_id: Int ,apiKey: String): Response<VideoResponse> {
        return tmdbApi.getMovieVideos(movie_id,apiKey)
    }

    suspend fun getMovieById(movie_id: Int, apiKey: String): Response<MovieResponse> {
        return tmdbApi.getMovieById(movie_id,apiKey)
    }
    suspend fun getReviews(movie_id: Int, apiKey: String): Response<ReviewResponse> {
        return tmdbApi.getMovieReviews(movie_id,apiKey)
    }
}