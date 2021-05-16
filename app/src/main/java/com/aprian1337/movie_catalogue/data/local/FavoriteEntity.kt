package com.aprian1337.movie_catalogue.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val title: String,
    val genres: String,
    val vote: String,
    val img: String,
    val release: String,
    val type: String
)