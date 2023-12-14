package com.example.moviescope

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviescope.navigationBar.movieList.MovieListFragment
import com.example.moviescope.screens.navigationBar.favList.FavFragment
import com.example.moviescope.screens.navigationBar.settings.SettingsFragment


class MainActivityViewModel : ViewModel() {

    private val _currentFragment = MutableLiveData<Fragment>()
    val currentFragment: LiveData<Fragment> get() = _currentFragment

    fun onNavItemClicked(itemId: Int) {
        when (itemId) {
            R.id.moveList -> _currentFragment.value = MovieListFragment()
            R.id.settings -> _currentFragment.value = SettingsFragment()
            R.id.favorites -> _currentFragment.value = FavFragment()
        }
    }
}
