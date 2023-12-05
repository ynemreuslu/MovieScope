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
    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> get() = _moviesLiveData

    private val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    fun getAllMovies(searchTerm: String) {
        val call = movieApi.getAllMovies(searchTerm, MovieApiConfig.API_KEY ,"movie")

        call.enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        _moviesLiveData.value = movieResponse.Search
                    }
                } else {
                    Log.e("MovieViewModel", "API çağrısı başarısız")
                }
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.e("MovieViewModel", "API çağrısı başarısız", t)
            }
        })
    }
}