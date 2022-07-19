package com.example.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Movie
import kotlinx.android.synthetic.main.movie_card_row.view.*

class MoviesAdapter(
    private val movieList: MutableList<Movie>?
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.movieTitle
        val releaseDate: TextView = itemView.movieGender
        val description: TextView = itemView.movieDescription
        val image: ImageView = itemView.listRowImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_card_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieItem = movieList?.get(position)

        val movieTitle = movieItem?.title
        val releaseDate = movieItem?.release_date
        val description = movieItem?.overview

        holder.title.text = movieTitle
        holder.releaseDate.text = releaseDate
        holder.description.text = description
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w300" + movieItem?.poster_path)
            .placeholder(R.drawable.ic_launcher_background)
//            .apply(RequestOptions.centerCropTransform())
            .override(300, 600)
            .into(holder.image)
    }

    override fun getItemCount() = movieList!!.size

    fun updateMovies(movies: List<Movie>) {
        movieList?.addAll(movies)
        notifyDataSetChanged()
    }
}
