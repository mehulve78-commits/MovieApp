package com.example.movieapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(@Query("q") query: String): MovieSearchResponse
}
