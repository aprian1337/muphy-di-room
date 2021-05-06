package com.aprian1337.movie_catalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.databinding.ActivityDetailBinding
import com.aprian1337.movie_catalogue.ui.AppViewModelFactory
import com.aprian1337.movie_catalogue.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID_MOVIETV = "extra_id_movietv"
        const val EXTRA_TYPE_TAG = "extra_type_tag"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLoading(true)
        binding.include2.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            setNavigationOnClickListener {
                finish()
            }
        }
        viewModel = ViewModelProvider(
            this,
            AppViewModelFactory.getInstance()
        ).get(DetailViewModel::class.java)
        val movieTvId = intent.getIntExtra(EXTRA_ID_MOVIETV, 0)
        val type = intent.getStringExtra(EXTRA_TYPE_TAG)
        if (type == "MOVIES") {
            viewModel.getDetailMovies(movieTvId).observe(this@DetailActivity, {
                changeData(it)
            })
        } else {
            viewModel.getDetailTvShows(movieTvId).observe(this@DetailActivity, {
                changeData(it)
            })
        }
    }

    private fun changeData(it: DetailMovieTv) {
        with(binding) {
            tvDetailTitle.text = it.title
            tvDetailGenre.text = it.genre
            tvDetailLength.text = it.release
            tvDetailParental.text = it.popularity
            tvDetailOverview.text = it.overview
            Glide.with(binding.root)
                .load(Constants.BASE_IMAGE_URL + it.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgDetailView)
        }
        setLoading(false)
    }

    private fun setLoading(state: Boolean) {
        with(binding) {
            if (state) {
                pbDetail.visibility = View.VISIBLE
            } else {
                pbDetail.visibility = View.GONE
            }
        }
    }
}