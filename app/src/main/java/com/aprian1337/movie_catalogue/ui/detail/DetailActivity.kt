package com.aprian1337.movie_catalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.databinding.ActivityDetailBinding
import com.aprian1337.movie_catalogue.models.MovieTv
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE_TV = "extra_movie_tv"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.include2.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            setNavigationOnClickListener{
                finish()
            }
        }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        val data = intent.getParcelableExtra<MovieTv>(EXTRA_MOVIE_TV) as MovieTv
        viewModel.setMovieTv(data)
        viewModel.getMovieTv().let {
            with(binding){
                tvTitle.text = it.title
                tvGenre.text = it.genre
                tvLength.text = it.length
                tvParental.text = it.parental
                tvOverview.text = it.overview
                Glide.with(binding.root)
                    .load(it.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgView)
            }
        }
    }
}