package com.example.moviescope.screens.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.movieDetail.MovieDetailViewModel
import com.example.moviescope.repo.MovieRepository
import java.lang.IllegalArgumentException

class MovieDetailsViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java))
            return MovieDetailViewModel(movieRepository) as T
        else
            throw IllegalArgumentException("Unkown ViewModel Class")

    }
}