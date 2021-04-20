package com.aprian1337.movie_catalogue.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aprian1337.movie_catalogue.ui.main.MainActivity
import com.aprian1337.movie_catalogue.databinding.ActivitySplashscreenBinding

class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val delayDuration : Long = 4000
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashscreenActivity, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, delayDuration)
    }
}