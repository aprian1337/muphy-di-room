package com.aprian1337.movie_catalogue.data.local

import androidx.paging.DataSource
import androidx.room.*
import com.aprian1337.movie_catalogue.utils.Constants

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(fav : FavoriteEntity)

    @Query("SELECT * FROM ${Constants.TABLE_FAVORITES} WHERE type=:type")
    fun getAllFav(type: String) : DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT count(*) FROM ${Constants.TABLE_FAVORITES} WHERE type=:type AND  id=:id")
    suspend fun checkFav(type: String, id: Int) : Int

    @Delete
    suspend fun deleteFav(favoriteEntity: FavoriteEntity)
}