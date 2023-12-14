package com.example.moviescope.models

data class MovieSearch(
    val Response: String,
    val Search: List<Movie>,
    val TotalResults: String
)