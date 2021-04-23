package com.aprian1337.movie_catalogue.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.databinding.ActivityMainBinding
import com.aprian1337.movie_catalogue.ui.main.movie.MoviesFragment
import com.aprian1337.movie_catalogue.ui.main.profile.ProfileFragment
import com.aprian1337.movie_catalogue.ui.main.tvshow.TvShowsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(MoviesFragment())

        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_movie -> {
                    addFragment(MoviesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_tvshow -> {
                    addFragment(TvShowsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_profile -> {
                    addFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.container, fragment)
            .commit()
    }

}