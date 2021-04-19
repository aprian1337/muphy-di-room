package com.aprian1337.movie_catalogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        val delayDuration : Long = 4000
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashscreenActivity, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, delayDuration)
    }
}