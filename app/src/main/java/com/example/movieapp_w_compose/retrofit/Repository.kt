package com.example.movieapp_w_compose.retrofit


import com.example.movieapp_w_compose.domain.model.GenreResponseEntity
import com.example.movieapp_w_compose.domain.model.MovieEntity
import com.example.movieapp_w_compose.domain.model.MovieResponseEntity
import com.example.movieapp_w_compose.domain.model.ReviewResponseEntity
import com.example.movieapp_w_compose.domain.model.VideoResponseEntity
import com.example.movieapp_w_compose.data.dto.toGenreResponseEntity
import com.example.movieapp_w_compose.data.dto.toMovieEntity
import com.example.movieapp_w_compose.data.dto.toMovieResponseEntity
import com.example.movieapp_w_compose.data.dto.toReviewResponseEntity
import com.example.movieapp_w_compose.data.dto.toVideoResponseEntity
import jakarta.inject.Inject

class Repository @Inject constructor(
    private val tmdbApi: TmdbService
) {

    suspend fun getPopularMovies(): MovieResponseEntity? {
        return tmdbApi.getPopularMovies().body()?.toMovieResponseEntity()
    }

    suspend fun getTopRatedMovies(): MovieResponseEntity? {
        return tmdbApi.getTopRatedMovies().body()?.toMovieResponseEntity()
    }

    suspend fun getNowPlayingMovies(): MovieResponseEntity? {
        return tmdbApi.getNowPlayingMovies().body()?.toMovieResponseEntity()
    }

    suspend fun getCategoryMovies(genreId: Int?): MovieResponseEntity? {
        return tmdbApi.getCategoryMovies(genreId).body()?.toMovieResponseEntity()
    }

    suspend fun getGenres(): GenreResponseEntity? {
        return tmdbApi.getGenres().body()?.toGenreResponseEntity()
    }

    suspend fun getMovies(): MovieResponseEntity? {
        return tmdbApi.getMovies().body()?.toMovieResponseEntity()
    }

    suspend fun getSearch(query: String): MovieResponseEntity? {
        return tmdbApi.searchMovies(query).body()?.toMovieResponseEntity()
    }

    suspend fun getVideos(movie_id: Int): VideoResponseEntity? {
        return tmdbApi.getMovieVideos(movie_id).body()?.toVideoResponseEntity()
    }

    suspend fun getMovieById(movie_id: Int): MovieEntity? {
        return tmdbApi.getMovieById(movie_id).body()?.toMovieEntity()
    }

    suspend fun getReviews(movie_id: Int): ReviewResponseEntity? {
        return tmdbApi.getMovieReviews(movie_id).body()?.toReviewResponseEntity()
    }

    suspend fun getSimilar(movie_id: Int): MovieResponseEntity? {
        return tmdbApi.getSimilar(movie_id).body()?.toMovieResponseEntity()
    }
}