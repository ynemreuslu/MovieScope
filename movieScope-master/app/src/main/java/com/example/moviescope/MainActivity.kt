package com.example.moviescope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.moviescope.databinding.ActivityMainBinding
import com.example.moviescope.navigationBar.favList.FavFragment
import com.example.moviescope.navigationBar.movieList.MovieListFragment
import com.example.moviescope.navigationBar.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationOnItemClick()


    }

    fun navigationOnItemClick() {
        val movieListFragment = MovieListFragment()
        val favFragment = FavFragment()
        val settingsFragment = SettingsFragment()
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.moveList -> setCurrentFragment(movieListFragment)
                R.id.favorites -> setCurrentFragment(favFragment)
                R.id.settings -> setCurrentFragment(settingsFragment)

            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
            setReorderingAllowed(true)
        }
    }

}