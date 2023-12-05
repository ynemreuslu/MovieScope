package com.example.moviescope.movieDetail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.models.MovieDetails
import com.example.moviescope.network.ApiClientBuilder
import com.example.moviescope.network.MovieApi
import com.example.moviescope.utils.MovieApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails
    val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    fun getDetailsMovies(imdId: String) {
        val call = movieApi.getDetailsMovies(imdId, MovieApiConfig.API_KEY)
        call.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful) {
                    _movieDetails.value = response.body()
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.e("Movie Details ViewModel", "The API call failed", t)
            }
        })
    }
}

