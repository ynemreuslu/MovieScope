package com.example.moviescope.models

import com.google.gson.annotations.SerializedName

data class MovieSearch(
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val search: List<Movie>,
    @SerializedName("TotalResult") val totalResults: String
)