package com.example.moviescope.screens.navigationBar.favList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.repo.MovieRepository


class FavFragmentViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavFragmentViewModel::class.java))
            return FavFragmentViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
