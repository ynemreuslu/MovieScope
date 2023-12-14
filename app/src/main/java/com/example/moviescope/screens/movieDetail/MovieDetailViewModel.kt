package com.example.moviescope.movieDetail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.models.MovieDetails
import com.example.moviescope.network.ApiClientBuilder
import com.example.moviescope.network.MovieApi
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieModel
import com.example.moviescope.screens.movieDetail.MovieDetailsActivity
import com.example.moviescope.utils.MovieApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails

    val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    val getFavMovies: MutableLiveData<List<MovieModel>> = MutableLiveData()

    private val _favoriteMovies = MutableLiveData<List<MovieModel>>()
    val favoriteMovies: LiveData<List<MovieModel>> get() = _favoriteMovies
    var mWeakReference: WeakReference<MovieDetailsActivity> = WeakReference(null)

    init {
        setMovie()
    }

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

    fun movieInsert(movie: MovieModel) {
        val currentFavoriteMovies = _favoriteMovies.value
        if (currentFavoriteMovies?.none { it.imdId == movie.imdId } == true) {
            viewModelScope.launch {
                movieRepository.movieInsert(movie)
                setMovie()
            }
        }
    }

    private fun setMovie() {
        viewModelScope.launch {
            _favoriteMovies.value = movieRepository.getMovieAll()
            getFavMovies.postValue(_favoriteMovies.value)
        }
    }
}
