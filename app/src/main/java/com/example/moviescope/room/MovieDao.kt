package com.example.moviescope.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun movieInsert(movieModel: MovieModel)

    @Query("DELETE FROM movie_table WHERE id=:id")
    suspend fun movieDelete(id: Int)

    @Query("SELECT * FROM movie_table")
    suspend fun getMovieAll(): List<MovieModel>

}