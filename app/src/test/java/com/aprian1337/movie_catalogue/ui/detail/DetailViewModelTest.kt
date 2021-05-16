package com.aprian1337.movie_catalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.repository.MainRepository
import com.aprian1337.movie_catalogue.utils.DummyData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieTv>

    @Mock
    private lateinit var observerCheck: Observer<Int>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovies() {
        val detailList = MutableLiveData<DetailMovieTv>()
        val detailData = DummyData.detailData
        detailList.value = detailData

        Mockito.`when`(repository.getDetailMovies("1")).thenReturn(detailList)
        val entity = viewModel.getDetailMovies(1).value
        verify(repository).getDetailMovies("1")
        Assert.assertNotNull(entity)

        viewModel.getDetailMovies(1).observeForever(observer)
        verify(observer).onChanged(detailData)
    }

    @Test
    fun getDetailTvShows() {
        val detailTvShowList = MutableLiveData<DetailMovieTv>()
        val detailData = DummyData.detailData
        detailTvShowList.value = detailData

        Mockito.`when`(repository.getDetailTvShows("1")).thenReturn(detailTvShowList)
        val entity = viewModel.getDetailTvShows(1).value
        verify(repository).getDetailTvShows("1")
        Assert.assertNotNull(entity)

        viewModel.getDetailTvShows(1).observeForever(observer)
        verify(observer).onChanged(detailData)
    }

    @Test
    fun checkFav() = runBlocking {
        var checkFavData = 1
        val checkLiveData = MutableLiveData<Int>()
        checkLiveData.value = checkFavData

        Mockito.`when`(repository.checkFav("MOVIES", 1)).thenReturn(checkLiveData)
        var checkEntity = viewModel.checkFav("MOVIES", 1).value
        verify(repository).checkFav("MOVIES", 1)
        Assert.assertNotNull(checkEntity)
        Assert.assertEquals(checkEntity, checkFavData)
        viewModel.checkFav("MOVIES", 1).observeForever(observerCheck)
        verify(observerCheck).onChanged(checkFavData)

        checkFavData = 0
        checkLiveData.value = checkFavData
        Mockito.`when`(repository.checkFav("TVSHOWS", 2)).thenReturn(checkLiveData)
        checkEntity = viewModel.checkFav("TVSHOWS", 2).value
        verify(repository).checkFav("TVSHOWS", 2)
        Assert.assertNotNull(checkEntity)
        Assert.assertEquals(checkEntity, checkFavData)
        viewModel.checkFav("TVSHOWS", 2).observeForever(observerCheck)
        verify(observerCheck).onChanged(checkFavData)
    }
}