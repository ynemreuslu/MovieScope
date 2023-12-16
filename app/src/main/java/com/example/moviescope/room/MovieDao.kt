package com.example.moviescope.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviescope.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun movieInsert(movie: MovieModel)

    @Delete
    suspend fun movieDelete(movie: MovieModel)



    @Query("SELECT * FROM movie")
    suspend fun getMovieAll(): List<MovieModel>
}
