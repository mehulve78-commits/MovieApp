package com.example.movieapp.data.api

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("description")
    val description: List<Movie>
)

data class Movie(
    @SerializedName("#TITLE")
    val title: String,
    @SerializedName("#IMDB_ID")
    val imdbId: String,
    @SerializedName("#IMG_POSTER")
    val poster: String
)
