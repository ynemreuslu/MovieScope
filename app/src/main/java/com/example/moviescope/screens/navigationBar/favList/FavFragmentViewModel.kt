package com.example.moviescope.screens.navigationBar.favList


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieModel
import kotlinx.coroutines.launch


class FavFragmentViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val getFavMovies: MutableLiveData<List<MovieModel>> = MutableLiveData()

    init {
        getMovieAll()
    }

    fun movieDelete(movie: MovieModel) {
        viewModelScope.launch {
            movieRepository.movieDelete(movie)
            getMovieAll()
        }
    }

    private fun getMovieAll() {
        viewModelScope.launch {
            getFavMovies.postValue(movieRepository.getMovieAll())
        }
    }
}
