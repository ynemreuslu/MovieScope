package com.example.moviescope.room

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("movie_table")
data class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("movie_poster") var moviePoster: String,
    @ColumnInfo("movie_title") var movieTitle: String,
    @ColumnInfo("movie_year") var movieYear: String,

)
