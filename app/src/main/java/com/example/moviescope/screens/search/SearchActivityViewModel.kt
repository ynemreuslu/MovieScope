package com.example.moviescope.screens.search

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
import retrofit2.Response
import javax.security.auth.callback.Callback

class SearchActivityViewModel : ViewModel() {

    private val _movieSearch = MutableLiveData<List<Movie>>()
    val movieSearch: LiveData<List<Movie>> get() = _movieSearch

    private val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    fun getSearh(search: String) {
        val call = movieApi.getAllMovies(search, MovieApiConfig.API_KEY, "movie")
        call.enqueue(object : retrofit2.Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        _movieSearch.value = movieResponse.Search
                    }
                } else {
                    Log.e("Search ViewModel", "API çağrısı başarısız")
                }
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                Log.e("Search View Model ", "API çağrısı başarısız", t)
            }
        })
    }


}