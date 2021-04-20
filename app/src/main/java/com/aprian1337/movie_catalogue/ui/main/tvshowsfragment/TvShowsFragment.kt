package com.aprian1337.movie_catalogue.ui.main.tvshowsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.movie_catalogue.databinding.FragmentTvShowsBinding
import com.aprian1337.movie_catalogue.ui.main.MovieTvAdapter
import com.aprian1337.movie_catalogue.ui.main.MovieTvFeaturedAdapter

class TvShowsFragment : Fragment() {

    private val movieTvAdapter by lazy { MovieTvAdapter() }
    private val movieTvFeaturedAdapter by lazy { MovieTvFeaturedAdapter() }
    private lateinit var viewModel : TvShowViewModel
    private var _binding : FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel::class.java)
        viewModel.getTvShow().observe(viewLifecycleOwner, {
            movieTvAdapter.setMovieTv(it)
        })
        viewModel.getTvShowFeatured().observe(viewLifecycleOwner, {
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
                layoutManager = LinearLayoutManager(activity, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }
}