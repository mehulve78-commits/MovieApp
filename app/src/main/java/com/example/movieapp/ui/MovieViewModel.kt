package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.Movie
import com.example.movieapp.domain.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _savedMovies = MutableStateFlow<List<com.example.movieapp.data.local.MovieEntity>>(emptyList())
    val savedMovies: StateFlow<List<com.example.movieapp.data.local.MovieEntity>> = _savedMovies

    val searchQuery = MutableStateFlow("Blade Runner")

    init {
        searchMovies(searchQuery.value)
        getSavedMovies()
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                _movies.value = repository.searchMovies(query)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            repository.saveMovie(movie)
        }
    }

    private fun getSavedMovies() {
        viewModelScope.launch {
            repository.getSavedMovies().collect {
                _savedMovies.value = it
            }
        }
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
