package com.example.moviescope.screens.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.moviescope.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clearButton.visibility = View.GONE
        onBackPrees()
        clearButton()
        visibilityClearButton()
        showSoftKeyboard(binding.searchEditText)
    }

    private fun onBackPrees() {
        binding.onBackPress.setOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun clearButton() {
        visibilityClearButton()
        binding.clearButton.setOnClickListener {
            binding.searchEditText.text.clear()

        }
    }

    private fun showSoftKeyboard(view: View) {

        if (view.requestFocus()) {
            val imm = getSystemService(InputMethodManager::class.java)
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun visibilityClearButton() {
        binding.searchEditText.addTextChangedListener {
            if (binding.searchEditText.text.isNotEmpty()) binding.clearButton.visibility =
                View.VISIBLE
            else binding.clearButton.visibility = View.GONE
        }
    }
}