package com.example.moviescope.network

import com.example.moviescope.models.MovieDetails
import com.example.moviescope.models.MovieSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    fun getAllMovies(
        @Query("s") searchTerm: String,
        @Query("apikey") apiKey: String,
        @Query("t") type: String,
    ): Call<MovieSearch>

    @GET("/")
    fun getDetailsMovies(
        @Query("i") imdId: String, @Query("apikey") apiKey: String
    ): Call<MovieDetails>
}