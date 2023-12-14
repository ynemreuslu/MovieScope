package com.example.moviescope.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun movieInsert(movie: MovieModel)

    @Delete
    suspend fun movieDelete(movie: MovieModel)

    @Query("SELECT * FROM movie WHERE imdId=:imdId")
    suspend fun getMovieByImdId(imdId: String): List<MovieModel>

    @Query("SELECT * FROM movie")
    suspend fun getMovieAll(): List<MovieModel>
}
