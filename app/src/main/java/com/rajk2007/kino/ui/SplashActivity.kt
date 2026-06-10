package com.rajk2007.kino.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.lagradost.cloudstream3.MainActivity
import com.lagradost.cloudstream3.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_splash_kino)
            runSplashAnimation()
        } catch (e: Exception) {
            e.printStackTrace()
            goToMain()
        }
    }

    private fun runSplashAnimation() {
        val kLetter = findViewById<TextView>(R.id.k_letter)
        val inoLetters = findViewById<TextView>(R.id.ino_letters)
        val redLine = findViewById<View>(R.id.red_line)
        val byRaj = findViewById<TextView>(R.id.by_raj)
        val cinemaRedefined = findViewById<TextView>(R.id.cinema_redefined)

        if (kLetter == null || inoLetters == null || redLine == null) {
            goToMain()
            return
        }

        Handler(Looper.getMainLooper()).postDelayed({
            kLetter.isVisible = true
            kLetter.startAnimation(TranslateAnimation(0f, 0f, -500f, 0f).apply {
                duration = 600
                interpolator = OvershootInterpolator()
            })
            Handler(Looper.getMainLooper()).postDelayed({
                inoLetters.isVisible = true
                inoLetters.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 400 })
            }, 300)
            Handler(Looper.getMainLooper()).postDelayed({
                redLine.isVisible = true
                redLine.scaleX = 0f
                redLine.animate().scaleX(1f).setDuration(500)
                    .setInterpolator(AccelerateDecelerateInterpolator()).start()
            }, 600)
            Handler(Looper.getMainLooper()).postDelayed({
                byRaj?.isVisible = true
                byRaj?.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 800 })
            }, 900)
            Handler(Looper.getMainLooper()).postDelayed({
                cinemaRedefined?.isVisible = true
                cinemaRedefined?.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 800 })
            }, 1200)
            Handler(Looper.getMainLooper()).postDelayed({
                goToMain()
            }, 2500)
        }, 300)
    }

    private fun goToMain() {
        try {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            finish()
        }
    }
}
