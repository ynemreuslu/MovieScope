package com.example.moviescope.screens.navigationBar.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.models.Movie
import com.example.moviescope.models.MovieSearch
import com.example.moviescope.navigationBar.movieList.MovieListFragment
import com.example.moviescope.network.ApiClientBuilder
import com.example.moviescope.network.MovieApi
import com.example.moviescope.utils.MovieApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference


class MovieListViewModel : ViewModel() {
    private val _moviesLiveDataFirst = MutableLiveData<List<Movie>>()
    val moviesLiveDataFirst: LiveData<List<Movie>> get() = _moviesLiveDataFirst

    private val _moviesLiveDataSecond = MutableLiveData<List<Movie>>()
    val moviesLiveDataSecond: LiveData<List<Movie>> get() = _moviesLiveDataSecond

    private val _movieLiveDataThird = MutableLiveData<List<Movie>>()
    val movieLiveDataThird: LiveData<List<Movie>> get() = _movieLiveDataThird

    private val movieApi = ApiClientBuilder.retrofitInstance.create(MovieApi::class.java)

    private val _selectedMovieId = MutableLiveData<String>()
    val selectedMovieId: LiveData<String> get() = _selectedMovieId

    private var mWeakReference: WeakReference<MovieListFragment> = WeakReference(null)

    fun fetchMovie(searchTerm: String, liveData: MutableLiveData<List<Movie>>) {
        val call = movieApi.getAllMovies(searchTerm, MovieApiConfig.API_KEY, "Movie")

        call.enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        liveData.value = movieResponse.Search
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

    fun fetchMovieFirst(searchTerm: String) {
        fetchMovie(searchTerm, _moviesLiveDataFirst)
    }

    fun fetchMovieSecond(searchTerm: String) {
        fetchMovie(searchTerm, _moviesLiveDataSecond)
    }

    fun fetchMovieThird(searchTerm: String) {
        fetchMovie(searchTerm, _movieLiveDataThird)
    }

    fun performSearch() {
        mWeakReference.get()?.clickBarButton()
    }

    fun onMovieItemClick(imdString: String) {
        _selectedMovieId.value = imdString
    }

    fun setFragment(movieListFragment: MovieListFragment) {
        mWeakReference = WeakReference(movieListFragment)
    }
}

