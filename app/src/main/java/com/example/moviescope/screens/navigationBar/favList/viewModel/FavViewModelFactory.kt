package com.example.moviescope.screens.navigationBar.favList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.repo.MovieRepository
import java.lang.IllegalArgumentException

class FavViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavFragmentViewModel::class.java))
            return  FavFragmentViewModel(movieRepository) as T

        else
            throw IllegalArgumentException("Unkown ViewModel Class")
    }
}