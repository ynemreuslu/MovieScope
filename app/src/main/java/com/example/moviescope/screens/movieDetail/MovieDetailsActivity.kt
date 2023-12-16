package com.example.moviescope.screens.movieDetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivityMovieDetailsBinding
import com.example.moviescope.movieDetail.MovieDetailViewModel
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieDatabase
import com.example.moviescope.room.MovieModel


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.lifecycleOwner = this

        val imdId = intent.getStringExtra("imd")

        val dao = MovieDatabase.getInstance(this).movieDao()
        val movieRepository = MovieRepository(dao)
        val viewModelFactory = MovieDetailsViewModelFactory(movieRepository)
        movieDetailViewModel =
            ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]

        imdId?.let { movieDetailViewModel.getDetailsMovies(it) }

        var isLike = false

        movieDetailViewModel.movieDetails.observe(this) { movieDetails ->
            binding.movie = movieDetails
            Glide.with(this).load(movieDetails.Poster).into(binding.movieDetailsPoster)

            val movie = MovieModel(
                imdId = movieDetails.imdbID,
                poster = movieDetails.Poster,
                title = movieDetails.Title,
                year = movieDetails.Year
            )

            updateLikeButton(movie)

            binding.favoritesButton.setOnClickListener {
                insertMovie(movie)
                binding.favoritesButton.setImageResource(R.drawable.ic_favorite)
            }
        }

        onBackPress()
    }

    private fun onBackPress() {
        binding.onBackPress.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun insertMovie(movie: MovieModel) {
        movieDetailViewModel.movieInsert(movie)
        Log.d("MovieDetailsActivity", "Inserting movie: $movie")
    }

    private fun updateLikeButton(movie: MovieModel) {
        val isMovieExists =
            movieDetailViewModel.favoriteMovies.value?.any { it.imdId == movie.imdId } == true
        if (isMovieExists) {
            binding.favoritesButton.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.favoritesButton.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}
