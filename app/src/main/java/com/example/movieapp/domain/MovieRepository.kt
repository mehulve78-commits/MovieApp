package com.example.movieapp.domain

import com.example.movieapp.data.api.ApiService
import com.example.movieapp.data.api.Movie
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.local.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) {
    suspend fun searchMovies(query: String): List<Movie> {
        return apiService.searchMovies(query).description
    }

    suspend fun saveMovie(movie: Movie) {
        val movieEntity = MovieEntity(
            imdbId = movie.imdbId,
            title = movie.title,
            poster = movie.poster
        )
        movieDao.insertMovie(movieEntity)
    }

    fun getSavedMovies(): Flow<List<MovieEntity>> {
        return movieDao.getSavedMovies()
    }
}
