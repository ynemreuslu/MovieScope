package com.example.moviescope.screens.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescope.databinding.ActivitySearchBinding
import com.example.moviescope.models.Movie
import com.example.moviescope.models.MovieDetails
import com.example.moviescope.screens.movieDetail.MovieDetailsActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchActivityViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clearButton.visibility = View.GONE
        setupBackPress()
        setupClearButton()
        setupClearButtonVisibility()
        showSoftKeyboard(binding.searchEditText)
        setupRecyclerView()

        searchViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]

        searchViewModel.movieSearch.observe(this) { movies ->
            searchAdapter.submitList(movies)
        }

        binding.searchRecyclerview.adapter = searchAdapter
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun setupBackPress() {
        binding.onBackPress.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupClearButton() {
        setupClearButtonVisibility()
        binding.clearButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager = getSystemService(InputMethodManager::class.java)
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun setupClearButtonVisibility() {
        binding.searchEditText.addTextChangedListener {
            binding.clearButton.visibility =
                if (binding.searchEditText.text.isNotEmpty()) View.VISIBLE else View.GONE
            searchViewModel.getSearh(binding.searchEditText.text.toString())
        }
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(
                "imd",
                movie.imdbID
            )
            startActivity(intent)
        }
    }

}