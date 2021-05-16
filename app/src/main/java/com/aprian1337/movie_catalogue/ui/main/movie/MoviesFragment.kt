package com.aprian1337.movie_catalogue.ui.main.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.databinding.FragmentMoviesBinding
import com.aprian1337.movie_catalogue.ui.AppViewModelFactory
import com.aprian1337.movie_catalogue.ui.detail.DetailActivity
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

    companion object{
        private const val TAG = "MOVIES"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setFeaturedLoading(true)
        setListLoading(true)
        viewModel = ViewModelProvider(this, AppViewModelFactory.getInstance(activity?.applicationContext!!)).get(MoviesViewModel::class.java)
        viewModel.apply {
            getNowMovies().observe(viewLifecycleOwner, {
                movieTvAdapter.setMovieTv(it)
                setListLoading(false)
            })

            getGenreMovies().observe(viewLifecycleOwner, {
                movieTvAdapter.setGenreMovieTv(it)
                movieTvFeaturedAdapter.setGenreMovieTv(it)
            })

            getFeaturedMovies().observe(viewLifecycleOwner, {
                movieTvFeaturedAdapter.setFeaturedMovieTv(it)
                setFeaturedLoading(false)
            })

        }

        movieTvAdapter.setOnItemClickCallback(object : MovieTvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTv) {
                selectedUser(data)
            }
        })
        movieTvFeaturedAdapter.setOnItemClickCallback(object : MovieTvFeaturedAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTv) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(data: MovieTv) {
        Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_TYPE_TAG, TAG)
            putExtra(DetailActivity.EXTRA_ID_MOVIETV, data.id)
            startActivity(this)
        }
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

    private fun setFeaturedLoading(state : Boolean){
        if(state){
            binding.pbFeatured.visibility = View.VISIBLE
        }else{
            binding.pbFeatured.visibility = View.GONE
        }
    }

    private fun setListLoading(state: Boolean){
        if(state){
            binding.pbList.visibility = View.VISIBLE
        }else{
            binding.pbList.visibility = View.GONE
        }
    }
}