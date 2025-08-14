package com.example.movieapp_w_compose.retrofit


import com.example.movieapp_w_compose.retrofit.model.GenreResponse
import com.example.movieapp_w_compose.retrofit.model.Movie
import com.example.movieapp_w_compose.retrofit.model.MovieResponse
import com.example.movieapp_w_compose.retrofit.model.ReviewResponse
import com.example.movieapp_w_compose.retrofit.model.VideoResponse
import jakarta.inject.Inject
import retrofit2.Response

class Repository @Inject constructor(
    private val tmdbApi: TmdbService
) {

    suspend fun getPopularMovies(): Response<MovieResponse> {
        return tmdbApi.getPopularMovies()

    }

    suspend fun getTopRatedMovies(): Response<MovieResponse> {
        return tmdbApi.getTopRatedMovies()
    }

    suspend fun getNowPlayingMovies(): Response<MovieResponse> {
        return tmdbApi.getNowPlayingMovies()
    }

    suspend fun getCategoryMovies(genreId: Int?): Response<MovieResponse> {
        return tmdbApi.getCategoryMovies(genreId)
    }
    suspend fun getGenres(): Response<GenreResponse> {
        return tmdbApi.getGenres()
    }
    suspend fun getMovies(): Response<MovieResponse> {
        return tmdbApi.getMovies()
    }
    suspend fun getSearch(query: String): Response<MovieResponse> {
        return tmdbApi.searchMovies( query)
    }

    suspend fun getVideos(movie_id: Int ): Response<VideoResponse> {
        return tmdbApi.getMovieVideos(movie_id)
    }

    suspend fun getMovieById(movie_id: Int): Response<Movie> {
        return tmdbApi.getMovieById(movie_id)
    }
    suspend fun getReviews(movie_id: Int): Response<ReviewResponse> {
        return tmdbApi.getMovieReviews(movie_id)
    }
    suspend fun getSimilar(movie_id: Int): Response<MovieResponse> {
        return tmdbApi.getSimilar(movie_id)
    }
}