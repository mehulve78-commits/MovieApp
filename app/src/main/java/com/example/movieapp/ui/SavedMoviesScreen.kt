package com.example.movieapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.data.local.MovieEntity

@Composable
fun SavedMoviesScreen(viewModel: MovieViewModel) {
    val savedMovies by viewModel.savedMovies.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(savedMovies) { movie ->
            SavedMovieItem(movie = movie)
        }
    }
}

@Composable
fun SavedMovieItem(movie: MovieEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                modifier = Modifier.size(100.dp, 150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}
