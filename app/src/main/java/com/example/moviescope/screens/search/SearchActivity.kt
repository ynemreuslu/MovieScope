package com.example.moviescope.screens.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivitySearchBinding
import com.example.moviescope.screens.movieDetail.MovieDetailsActivity

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchActivityViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]
        searchViewModel.setWeakReference(this)

        binding.viewModel = searchViewModel
        binding.clearButton.visibility = View.GONE
        showSoftKeyboard(binding.searchEditText)

        searchViewModel.getSearh(binding.searchEditText.text.toString())
        searchViewModel.movieSearch.observe(this) { movies ->
            searchAdapter.submitList(movies)
        }

        searchAdapter = SearchAdapter { imdId ->
            handleMovieItemClick(imdId)
        }

        setupRecyclerView()
        setupClearButtonVisibility()
    }

    fun setupBackPress() {
        onBackPressedDispatcher.onBackPressed()
    }

    fun setupClearButton() {
        setupClearButtonVisibility()
        binding.searchEditText.text.clear()
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager = getSystemService(InputMethodManager::class.java)
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun setupClearButtonVisibility() {
        binding.searchEditText.addTextChangedListener {
            binding.clearButton.visibility =
                if (binding.searchEditText.text.isNotEmpty()) View.VISIBLE else View.GONE
            searchViewModel.getSearh(binding.searchEditText.text.toString())
        }
    }

    private fun setupRecyclerView() {
        binding.searchRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }
    }

    private fun handleMovieItemClick(imdId: String) {
        navigateToMovieDetails(imdId)
        searchViewModel.movieSelected(imdId)
    }

    private fun navigateToMovieDetails(imdId: String) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("imd", imdId)
        startActivity(intent)
    }
}
