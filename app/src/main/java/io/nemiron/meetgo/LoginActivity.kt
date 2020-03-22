package io.nemiron.meetgo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.nemiron.meetgo.databinding.ActivityLoginBinding
import io.nemiron.meetgo.helpers.AppPrefs
import io.nemiron.meetgo.onboarding.OnBoardingActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        super.onCreate(savedInstanceState)

        if (AppPrefs.isFirstTimeLaunch)
            goToOnBoarding()
        else if (AppPrefs.isLogged)
            goToNavigationActivity()

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
    }

    private fun goToOnBoarding() {
        startActivity(Intent(this, OnBoardingActivity::class.java))
        finish()
    }

    private fun goToNavigationActivity() {
        startActivity(Intent(this, NavigationActivity::class.java))
        finish()
    }

    private fun setAppTheme() = setTheme(R.style.AppTheme)
}