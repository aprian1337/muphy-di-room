package com.aprian1337.movie_catalogue.data

import androidx.paging.DataSource
import com.aprian1337.movie_catalogue.data.local.FavoriteDao
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity

class LocalDataSource private constructor(private val mFavoriteDao : FavoriteDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(favoriteDao : FavoriteDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }
    fun getAllFav(type : String) :DataSource.Factory<Int, FavoriteEntity> = mFavoriteDao.getAllFav(type)
    suspend fun checkFav(type: String, id: Int) : Int = mFavoriteDao.checkFav(type, id)
    suspend fun addFav(favoriteEntity: FavoriteEntity) = mFavoriteDao.addFav(favoriteEntity)
    suspend fun deleteFav(favoriteEntity : FavoriteEntity){
        mFavoriteDao.deleteFav(favoriteEntity)
    }
}