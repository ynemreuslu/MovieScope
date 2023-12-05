package com.example.moviescope.models

data class MovieSearch(
    val response: String,
    val search: List<Movie>,
    val totalResults: String
)