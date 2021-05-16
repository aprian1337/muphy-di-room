package com.aprian1337.movie_catalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getDetailMovies(id : Int) : LiveData<DetailMovieTv> = mainRepository.getDetailMovies(id.toString())
    fun getDetailTvShows(id : Int) : LiveData<DetailMovieTv> = mainRepository.getDetailTvShows(id.toString())
    fun addFav(favoriteEntity: FavoriteEntity){
        viewModelScope.launch(Dispatchers.IO){
            mainRepository.addFav(favoriteEntity)
        }
    }
    fun checkFav(type: String, id: Int) : LiveData<Int> = mainRepository.checkFav(type, id)
    fun deleteFav(favoriteEntity : FavoriteEntity){
        viewModelScope.launch(Dispatchers.IO){
            mainRepository.deleteFav(favoriteEntity)
        }
    }
}