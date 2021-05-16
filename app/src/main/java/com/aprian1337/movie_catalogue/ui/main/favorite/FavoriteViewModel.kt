package com.aprian1337.movie_catalogue.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mainRepository: MainRepository) : ViewModel() {

    companion object{
        const val TAG_MOVIES = "MOVIES"
        const val TAG_TVSHOWS = "TVSHOWS"
    }

    fun getAllFavMovie() : LiveData<PagedList<FavoriteEntity>> = mainRepository.getAllFav(TAG_MOVIES)
    fun getAllFavTvShows() : LiveData<PagedList<FavoriteEntity>> = mainRepository.getAllFav(TAG_TVSHOWS)

    fun setFav(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch(Dispatchers.IO){
            mainRepository.deleteFav(favoriteEntity)
        }
    }

    fun addFav(favoriteEntity: FavoriteEntity){
        viewModelScope.launch(Dispatchers.IO){
            mainRepository.addFav(favoriteEntity)
        }
    }
}