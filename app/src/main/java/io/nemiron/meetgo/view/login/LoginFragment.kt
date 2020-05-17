package io.nemiron.meetgo.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nemiron.meetgo.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val appPrefs: AppPrefs by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDestination()
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        initViews()

        return loginBinding.root
    }

    private fun setDestination() {
        if (appPrefs.isFirstTimeLaunch)
            navigateToOnBoarding()
        else if (appPrefs.isLogged)
            navigateToHome()
    }

    private fun initViews() {
        loginBinding.forgotPassword.setOnClickListener { navigateToForgotPassword() }
        loginBinding.toRegistrationButton.setOnClickListener { navigateToRegistration() }
    }

    private fun navigateToOnBoarding() =
        findNavController().navigate(R.id.action_login_to_onBoarding)

    private fun navigateToRegistration() =
        findNavController().navigate(R.id.action_login_to_registration)

    private fun navigateToForgotPassword() =
        findNavController().navigate(R.id.action_login_to_forgotPassword)

    private fun navigateToHome() = (activity as MainActivity).setApplicationNavigation()
}
