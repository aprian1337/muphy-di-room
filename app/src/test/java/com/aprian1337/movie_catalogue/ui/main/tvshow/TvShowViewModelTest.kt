package com.aprian1337.movie_catalogue.ui.main.tvshow

import com.aprian1337.movie_catalogue.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun init(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        viewModel.setTvShow(DummyData.getTvShows())
        assertNotNull(viewModel.getTvShow())
        assertEquals(viewModel.getTvShow().size, 15)
        assertEquals(DummyData.getTvShows(), viewModel.getTvShow())
    }

    @Test
    fun getTvShowFeatured() {
        viewModel.setTvShowFeatured(DummyData.getFeaturedTvShows())
        assertNotNull(viewModel.getTvShowFeatured())
        assertEquals(viewModel.getTvShowFeatured().size, 6)
        assertEquals(DummyData.getFeaturedTvShows(), viewModel.getTvShowFeatured())
    }
}