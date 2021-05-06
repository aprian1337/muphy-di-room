package com.aprian1337.movie_catalogue.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.databinding.ListFeaturedBinding
import com.aprian1337.movie_catalogue.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieTvFeaturedAdapter : RecyclerView.Adapter<MovieTvFeaturedAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: ListFeaturedBinding) : RecyclerView.ViewHolder(binding.root)

    private var setOnItemClickCallback : OnItemClickCallback?= null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        setOnItemClickCallback = onItemClickCallback
    }

    private val featuredMovieTv = mutableListOf<MovieTv>()
    private val genres = mutableListOf<GenresItem>()

    fun setGenreMovieTv(list : List<GenresItem>){
        genres.clear()
        genres.addAll(list)
        notifyDataSetChanged()
    }

    fun setFeaturedMovieTv(list : List<MovieTv>){
        featuredMovieTv.clear()
        featuredMovieTv.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = featuredMovieTv[position]
        var genreString = ""
        for(genreId in data.genre){
            for(genre in genres){
                if (genreId == genre.id) {
                    if (genreId != data.genre[data.genre.size - 1]) {
                        genreString += genre.name + ", "
                        break
                    } else {
                        genreString += genre.name
                        break
                    }
                }
            }
        }
        with(holder.binding){
            Glide.with(root.context)
                .load(Constants.BASE_IMAGE_URL + data.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewFeatured)
            tvFeaturedTitle.text = data.title
            root.setOnClickListener{
                setOnItemClickCallback?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount(): Int = featuredMovieTv.size

    interface OnItemClickCallback{
        fun onItemClicked(data : MovieTv)
    }
}