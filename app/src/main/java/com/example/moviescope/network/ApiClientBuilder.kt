package com.example.moviescope.network

import com.example.moviescope.utils.MovieApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientBuilder {
    val retrofitInstance: Retrofit = Retrofit.Builder()
        .baseUrl(MovieApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
