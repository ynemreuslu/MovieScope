package com.example.moviescope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.viewModel = mainActivityViewModel

        navigationOnItemClick()

        mainActivityViewModel.currentFragment.observe(this) { fragment ->
            if (fragment != null) {
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainerView, fragment)
                    setReorderingAllowed(true)
                }
            }
        }
    }

    private fun navigationOnItemClick() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            mainActivityViewModel.onNavItemClicked(item.itemId)
            true
        }
    }
}
