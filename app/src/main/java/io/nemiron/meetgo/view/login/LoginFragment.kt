package io.nemiron.meetgo.view.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.databinding.FragmentLoginBinding


class LoginFragment(private val appPrefs: AppPrefs) : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDestination()
        initViews()
    }

    private fun setDestination() {
        if (appPrefs.isFirstTimeLaunch)
            navigateToOnBoarding()
        else if (appPrefs.isLogged)
            navigateToHome()
    }

    private fun initViews() {
        binding.forgotPassword.setOnClickListener { navigateToForgotPassword() }
        binding.toRegistrationButton.setOnClickListener { navigateToRegistration() }
    }

    private fun navigateToOnBoarding() =
        findNavController().navigate(R.id.action_login_to_onBoarding)

    private fun navigateToRegistration() =
        findNavController().navigate(R.id.action_login_to_registration)

    private fun navigateToForgotPassword() =
        findNavController().navigate(R.id.action_login_to_forgotPassword)

    private fun navigateToHome() = (activity as MainActivity).setApplicationNavigation()
}
