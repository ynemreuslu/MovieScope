package com.example.moviescope.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviescope.models.Movie
import com.example.moviescope.room.MovieDao
import com.example.moviescope.room.MovieModel

class MovieRepository(val movieDao: MovieDao) {

    suspend fun movieInsert(movieModel: MovieModel) {
        movieDao.movieInsert(movieModel)
    }

    suspend fun movieDelete(id : Int) {
        movieDao.movieDelete(id)
    }

    suspend fun getMovieAll(): List<MovieModel> {
        return movieDao.getMovieAll()
    }


}