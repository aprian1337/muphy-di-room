package com.aprian1337.movie_catalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.ui.main.favorite.FavoriteViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteEntity>>

    @Mock
    private lateinit var pagedList : PagedList<FavoriteEntity>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getFavorite(){
        val favoriteData = pagedList
        `when`(favoriteData.size).thenReturn(5)
        val favoriteLiveData = MutableLiveData<PagedList<FavoriteEntity>>()
        favoriteLiveData.value = favoriteData

        `when`(repository.getAllFav("MOVIES")).thenReturn(favoriteLiveData)
        var favoriteEntity = viewModel.getAllFavMovie().value
        verify(repository).getAllFav("MOVIES")
        Assert.assertNotNull(favoriteEntity)
        if (favoriteEntity != null) {
            Assert.assertEquals(5, favoriteEntity.size)
        }

        viewModel.getAllFavMovie().observeForever(observer)
        verify(observer).onChanged(favoriteData)

        `when`(repository.getAllFav("TVSHOWS")).thenReturn(favoriteLiveData)
        favoriteEntity = viewModel.getAllFavTvShows().value
        verify(repository).getAllFav("TVSHOWS")
        Assert.assertNotNull(favoriteEntity)
        if (favoriteEntity != null) {
            Assert.assertEquals(5, favoriteEntity.size)
        }

        viewModel.getAllFavTvShows().observeForever(observer)
        verify(observer).onChanged(favoriteData)
    }
}