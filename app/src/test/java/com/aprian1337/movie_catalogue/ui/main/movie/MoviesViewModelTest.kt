package com.aprian1337.movie_catalogue.ui.main.movie

import com.aprian1337.movie_catalogue.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun init(){
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        viewModel.setMovies(DummyData.getMovies())
        assertNotNull(viewModel.getMovies())
        assertEquals(viewModel.getMovies().size, 15)
        assertEquals(DummyData.getMovies(), viewModel.getMovies())
    }

    @Test
    fun getFeaturedMovies() {
        viewModel.setMoviesFeatured(DummyData.getFeaturedMovies())
        assertNotNull(viewModel.getMoviesFeatured())
        assertEquals(viewModel.getMoviesFeatured().size, 6)
        assertEquals(DummyData.getFeaturedMovies(), viewModel.getMoviesFeatured())
    }
}