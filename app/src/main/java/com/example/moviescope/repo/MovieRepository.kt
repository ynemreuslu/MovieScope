package com.example.moviescope.repo

import com.example.moviescope.room.MovieDao
import com.example.moviescope.room.MovieModel

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun movieInsert(movie: MovieModel) {
        movieDao.movieInsert(movie)
    }

    suspend fun movieDelete(movie: MovieModel) {
        movieDao.movieDelete(movie)
    }


    suspend fun getMovieAll(): List<MovieModel> {
        return movieDao.getMovieAll()
    }
}
