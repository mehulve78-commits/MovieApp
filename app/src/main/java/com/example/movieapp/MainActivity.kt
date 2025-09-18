package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.api.RetrofitInstance
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.ui.MainScreen
import com.example.movieapp.ui.MovieViewModel
import com.example.movieapp.ui.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MovieDatabase.getDatabase(this)
        val repository = MovieRepository(RetrofitInstance.api, database.movieDao())
        val viewModelFactory = MovieViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}
