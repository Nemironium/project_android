package io.nemiron.meetgo.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.nemiron.meetgo.NavigationActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.databinding.FragmentLoginBinding
import io.nemiron.meetgo.view.on_boarding.OnBoardingActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val appPrefs: AppPrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppTheme()
        super.onCreate(savedInstanceState)

        if (appPrefs.isFirstTimeLaunch)
            goToOnBoarding()
        else if (appPrefs.isLogged)
            goToNavigationActivity()

        loginBinding = FragmentLoginBinding.inflate(layoutInflater)
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