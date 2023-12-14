package com.example.moviescope.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var imdId: String,
    var title: String,
    var poster: String,
    var year: String
)
