package com.aprian1337.movie_catalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.databinding.ActivityDetailBinding
import com.aprian1337.movie_catalogue.ui.AppViewModelFactory
import com.aprian1337.movie_catalogue.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_ID_MOVIETV = "extra_id_movietv"
        const val EXTRA_TYPE_TAG = "extra_type_tag"
    }

    private var FLAG = 0
    private lateinit var type : String
    private lateinit var favoriteEntity: FavoriteEntity
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
            AppViewModelFactory.getInstance(application)
        ).get(DetailViewModel::class.java)
        val movieTvId = intent.getIntExtra(EXTRA_ID_MOVIETV, 0)
        type = intent.getStringExtra(EXTRA_TYPE_TAG).toString()
        if (type == "MOVIES") {
            viewModel.getDetailMovies(movieTvId).observe(this@DetailActivity, {
                changeData(it)
                favoriteEntity = FavoriteEntity(
                    it.id,
                    it.title,
                    it.genre,
                    it.popularity,
                    it.image,
                    it.release,
                    type,
                )
            })
        } else {
            viewModel.getDetailTvShows(movieTvId).observe(this@DetailActivity, {
                changeData(it)
                favoriteEntity = FavoriteEntity(
                    it.id,
                    it.title,
                    it.genre,
                    it.popularity,
                    it.image,
                    it.release,
                    type,
                )
            })
        }
        viewModel.checkFav(type, movieTvId).observe(this@DetailActivity, {
            if(it != 0){
                FLAG = 1
                setFav(true)
            }else{
                FLAG = 0
            }
        })
        binding.btnFav.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                R.id.btn_fav->{
                    Log.d("FAVENTITY", favoriteEntity.toString())
                    if(FLAG==0){
                        setFav(true)
                        viewModel.addFav(favoriteEntity)
                        val snackbar = Snackbar.make(
                            v, "Added to favorites",
                            Snackbar.LENGTH_LONG
                        )
                        with(snackbar){
                            this.setAction("DISMISS"){
                                this.dismiss()
                            }
                            this.show()
                        }
                        FLAG = 1
                    }else{
                        setFav(false)
                        viewModel.deleteFav(favoriteEntity)
                        val snackbar = Snackbar.make(
                            v, "Removed from favorites",
                            Snackbar.LENGTH_LONG
                        )
                        with(snackbar){
                            this.setAction("DISMISS"){
                                this.dismiss()
                            }
                            this.show()
                        }
                        FLAG = 0
                    }
                }
            }
        }
    }

    fun setFav(state : Boolean){
        if(state){
            binding.btnFav.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        }else{
            binding.btnFav.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}