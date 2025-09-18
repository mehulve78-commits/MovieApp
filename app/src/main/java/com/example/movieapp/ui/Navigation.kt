package com.example.movieapp.ui

sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie_screen")
    object SavedMoviesScreen : Screen("saved_movies_screen")
}
