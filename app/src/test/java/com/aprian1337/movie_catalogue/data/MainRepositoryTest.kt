package com.aprian1337.movie_catalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.utils.DummyData
import com.aprian1337.movie_catalogue.utils.LiveDataTestUtil
import com.aprian1337.movie_catalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MainRepositoryTest {
    private val remoteApi = mock(NetworkDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val fakeRepository = FakeRepository(remoteApi, localDataSource)

    private val movieResponse = DummyData.moviesResponse
    private val tvShowsResponse = DummyData.tvShowsResponse
    private val detailTvShowResponse = DummyData.detailTvShowResponse
    private val detailTvShow = DummyData.detailTvShow
    private val detailMovieResponse = DummyData.detailMovieResponse
    private val detailMovie = DummyData.detailMovie
    private val genreResponse = DummyData.genreResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getFeaturedMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadMoviesCallback).apply {
                    onMoviesReceived(movieResponse)
                }
            }.`when`(remoteApi).getFeaturedMovies(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getFeaturedMovies())
        GlobalScope.launch {
            verify(remoteApi).getFeaturedMovies(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.movieResponse.size)
    }

    @Test
    fun getNowMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadMoviesCallback).apply {
                    onMoviesReceived(movieResponse)
                }
            }.`when`(remoteApi).getNowMovies(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getNowMovies())
        GlobalScope.launch {
            verify(remoteApi).getNowMovies(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.movieResponse.size)
    }

    @Test
    fun getFeaturedTvShows() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadTvShowsCallback).apply {
                    onTvShowsReceived(tvShowsResponse)
                }
            }.`when`(remoteApi).getFeaturedTvShows(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getFeaturedTvShows())
        GlobalScope.launch {
            verify(remoteApi).getFeaturedTvShows(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.tvShowsResponse.size)
    }

    @Test
    fun getOnAirTvShows() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadTvShowsCallback).apply {
                    onTvShowsReceived(tvShowsResponse)
                }
            }.`when`(remoteApi).getOnAirTvShows(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getOnAirTvShows())
        GlobalScope.launch {
            verify(remoteApi).getOnAirTvShows(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.tvShowsResponse.size)
    }

    @Test
    fun getDetailTvShow() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as NetworkDataSource.LoadDetailTvShowCallback).apply {
                    onDetailTvShowReceived(detailTvShowResponse)
                }
            }.`when`(remoteApi).getTvShowsDetail(eq(detailTvShowResponse.id.toString()), any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getDetailTvShows(detailTvShowResponse.id.toString()))
        GlobalScope.launch {
            verify(remoteApi).getTvShowsDetail(eq(detailTvShowResponse.id.toString()), any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data, this.detailTvShow)
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            doAnswer {
                (it.arguments[1] as NetworkDataSource.LoadDetailMovieCallback).apply {
                    onDetailMovieReceived(detailMovieResponse)
                }
            }.`when`(remoteApi).getMoviesDetail(eq(detailMovieResponse.id.toString()), any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getDetailMovies(detailMovieResponse.id.toString()))
        GlobalScope.launch {
            verify(remoteApi).getMoviesDetail(eq(detailMovieResponse.id.toString()), any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data, this.detailMovie)
    }

    @Test
    fun getGenreMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadGenreCallback).apply {
                    onGenreReceived(genreResponse)
                }
            }.`when`(remoteApi).getGenreMovies(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getGenreMovies())
        GlobalScope.launch {
            verify(remoteApi).getGenreMovies(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.genreResponse.size)
    }

    @Test
    fun getGenreTvShow() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as NetworkDataSource.LoadGenreCallback).apply {
                    onGenreReceived(genreResponse)
                }
            }.`when`(remoteApi).getGenreTvShows(any())
        }
        val data = LiveDataTestUtil.getValue(fakeRepository.getGenreTvShows())
        GlobalScope.launch {
            verify(remoteApi).getGenreTvShows(any())
        }
        Assert.assertNotNull(data)
        Assert.assertEquals(data.size, this.genreResponse.size)
    }

    @Test
    fun getAllFav(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(localDataSource.getAllFav("MOVIES")).thenReturn(dataSourceFactory)
        fakeRepository.getAllFav("MOVIES")

        var favoriteEntity = PagedListUtil.mockPagedList(DummyData.favorite)
        verify(localDataSource).getAllFav("MOVIES")
        Assert.assertNotNull(favoriteEntity)
        Assert.assertEquals(DummyData.favorite.size, favoriteEntity.size)

        `when`(localDataSource.getAllFav("TVSHOWS")).thenReturn(dataSourceFactory)
        fakeRepository.getAllFav("TVSHOWS")

        favoriteEntity = PagedListUtil.mockPagedList(DummyData.favorite)
        verify(localDataSource).getAllFav("TVSHOWS")
        Assert.assertNotNull(favoriteEntity)
        Assert.assertEquals(DummyData.favorite.size, favoriteEntity.size)
    }

    @Test
    fun checkFav() = runBlocking{
        val checkData = 1
        `when`(localDataSource.checkFav("MOVIES", 1)).thenReturn(checkData)
        var data = LiveDataTestUtil.getValue(fakeRepository.checkFav("MOVIES", 1))
        verify(localDataSource).checkFav("MOVIES", 1)
        Assert.assertNotNull(data)
        Assert.assertEquals(data, checkData)

        `when`(localDataSource.checkFav("TVSHOWS", 2)).thenReturn(checkData)
        data = LiveDataTestUtil.getValue(fakeRepository.checkFav("TVSHOWS", 2))
        verify(localDataSource).checkFav("TVSHOWS", 2)
        Assert.assertNotNull(data)
        Assert.assertEquals(data, checkData)
    }

}