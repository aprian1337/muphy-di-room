package com.aprian1337.movie_catalogue.ui.main.moviesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.movie_catalogue.databinding.FragmentMoviesBinding
import com.aprian1337.movie_catalogue.ui.main.MovieTvAdapter
import com.aprian1337.movie_catalogue.ui.main.MovieTvFeaturedAdapter

class MoviesFragment : Fragment() {

    private var _binding : FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MoviesViewModel
    private val movieTvAdapter by lazy { MovieTvAdapter() }
    private val movieTvFeaturedAdapter by lazy { MovieTvFeaturedAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel::class.java)
        viewModel.getMovies().observe(viewLifecycleOwner, {
            movieTvAdapter.setMovieTv(it)
        })
        viewModel.getFeaturedMovies().observe(viewLifecycleOwner, {
            movieTvFeaturedAdapter.setFeaturedMovieTv(it)
        })
    }

    private fun setupRv(){
        with(binding){
            rvListMovies.apply {
                adapter = movieTvAdapter
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
            }
            rvFeaturedMovie.apply {
                adapter = movieTvFeaturedAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }
}