package com.example.moviescope.screens.navigationBar.movieList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviescope.R
import com.example.moviescope.databinding.MovieItemBinding
import com.example.moviescope.models.Movie


class MovieListAdapter(private val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: MovieItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false)

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movies[position]
        holder.binding.movie = movie
        holder.bind(movie)
    }

    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide.with(binding.root.context).load(movie.Poster).into(binding.moviePoster)
            binding.movie = movie

            binding.root.setOnClickListener {
                onClickListener(movie.imdbID)
            }
        }
    }
}
