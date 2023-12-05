package com.example.moviescope.screens.movieDetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivityMovieDetailsBinding
import com.example.moviescope.movieDetail.MovieDetailViewModel
import com.example.moviescope.repo.MovieRepository
import com.example.moviescope.room.MovieDatabase
import com.example.moviescope.room.MovieModel
import kotlinx.coroutines.launch


class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViewModel()

        val imdId = intent.getStringExtra("imd")
        imdId?.let { movieDetailViewModel.getDetailsMovies(it) }

        observeViewModel()
        setupBackButton()
        setupFavoritesButton()
    }

    private fun initializeViewModel() {
        val dao = MovieDatabase.getDatabase(this).getMovieDao()
        movieRepository = MovieRepository(dao)
        val viewModelFactory = MovieDetailsViewModelFactory(movieRepository)
        movieDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    private fun observeViewModel() {
        movieDetailViewModel.movieDetails.observe(this) { movieDetails ->
            if (movieDetails != null) {
                binding.movieDetailsPoster.let { imageView ->
                    Glide.with(this).load(movieDetails.Poster).into(imageView)
                }

                binding.movieDetailsActor.text =
                    "${getString(R.string.actor)} : ${movieDetails.Actors}"
                binding.movieDetailsTitle.text =
                    "${getString(R.string.title)} : ${movieDetails.Title}"
                binding.movieDetailsDirector.text =
                    "${getString(R.string.director)} : ${movieDetails.Director}"
                binding.movieDetailsWriter.text =
                    "${getString(R.string.writer)} : ${movieDetails.Writer}"
                binding.movieDetailsIMD.text = "IMD : ${movieDetails.imdbRating}"
                binding.movieDetailsYear.text = "${getString(R.string.year)} :${movieDetails.Year}"
            } else {
                Log.e("MovieDetailsActivity", "Movie not found")
            }
        }
    }

    private fun setupBackButton() {
        binding.topAppBar.setNavigationOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupFavoritesButton() {
        binding.favoritesButton.setOnClickListener {
            movieDetailViewModel.movieInsert()
            binding.favoritesButton.setImageResource(R.drawable.ic_favorite)
        }
    }
}
