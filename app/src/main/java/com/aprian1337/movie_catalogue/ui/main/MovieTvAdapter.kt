package com.aprian1337.movie_catalogue.ui.main
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.GenresItem
import com.aprian1337.movie_catalogue.databinding.ListMovieTvBinding
import com.aprian1337.movie_catalogue.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MovieTvAdapter : RecyclerView.Adapter<MovieTvAdapter.MainViewHolder>() {
    inner class MainViewHolder(val binding: ListMovieTvBinding) : RecyclerView.ViewHolder(binding.root)

    private val movies = mutableListOf<MovieTv>()
    private val genres = mutableListOf<GenresItem>()

    private var setOnItemClickCallback : OnItemClickCallback? = null
    
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.setOnItemClickCallback = onItemClickCallback
    }
    
    fun setMovieTv(list : List<MovieTv>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun setGenreMovieTv(list : List<GenresItem>){
        genres.clear()
        genres.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movieTv = movies[position]
        var genreString = ""
        for(genreId in movieTv.genre){
            for(genre in genres){
                if (genreId == genre.id) {
                    if (genreId != movieTv.genre[movieTv.genre.size - 1]) {
                        genreString += genre.name + ", "
                        break
                    } else {
                        genreString += genre.name
                        break
                    }
                }
            }
        }
        holder.binding.apply {
            Glide.with(root.context)
                .load(Constants.BASE_IMAGE_URL + movieTv.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            tvTitle.text = movieTv.title
            tvGenre.text = genreString
            tvPopularity.text = movieTv.popularity
            root.setOnClickListener{
                setOnItemClickCallback?.onItemClicked(movieTv)
            }
        }
    }

    override fun getItemCount(): Int = movies.size
    
    interface OnItemClickCallback{
        fun onItemClicked(data: MovieTv)
    }
}