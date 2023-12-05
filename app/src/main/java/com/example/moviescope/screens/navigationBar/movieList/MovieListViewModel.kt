package com.example.moviescope.screens.navigationBar.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.models.Movie
import com.example.moviescope.models.MovieSearch
import com.example.moviescope.network.ApiClientBuilder
import com.example.moviescope.network.MovieApi
import com.example.moviescope.utils.MovieApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel : ViewModel() {
    private val _moviesLiveDataFirst = MutableLiveData<List<Movie>>()
    val moviesLiveDataFirst: LiveData<List<Movie>> get() = _moviesLiveDataFirst

    private val _moviesLiveDataSecond = MutableLiveData<List<Movie>>()
    val moviesLiveDataSecond: LiveData<List<Movie>> get() = _moviesLiveDataSecond

    private val _movieLiveDataThird = MutableLiveData<List<Movie>>()
    val movieLiveDataThird : MutableLiveData<List<Movie>> get() = _movieLiveDataThird

    private val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    fun fetchMovieFirst(searchTerm: String) {
        val call = movieApi.getAllMovies(searchTerm, MovieApiConfig.API_KEY, "Movie")

        call.enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        _moviesLiveDataFirst.value = movieResponse.Search

                    }
                } else {
                    Log.e("MovieViewModel", "API call failed")
                }
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.e("MovieViewModel", "API call failed", t)
            }
        })
    }

    fun fetchMovieSecond(searchTerm: String) {
        val call = movieApi.getAllMovies(searchTerm, MovieApiConfig.API_KEY, "Movie")

        call.enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {

                        _moviesLiveDataSecond.value = movieResponse.Search

                    }
                } else {
                    Log.e("MovieViewModel", "API call failed")
                }
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.e("MovieViewModel", "API call failed", t)
            }
        })
    }

    fun fetchMovieThird(searchTerm: String) {
        val call = movieApi.getAllMovies(searchTerm, MovieApiConfig.API_KEY, "Movie")

        call.enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {

                        _movieLiveDataThird.value = movieResponse.Search

                    }
                } else {
                    Log.e("MovieViewModel", "API call failed")
                }
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.e("MovieViewModel", "API call failed", t)
            }
        })
    }
}