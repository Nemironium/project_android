package io.nemiron.meetgo.view.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.view.ui.main_activity.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.helpers.AuthorizationHelper
import io.nemiron.meetgo.databinding.FragmentLoginBinding


class LoginFragment(private val authorizationHelper: AuthorizationHelper) : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDestination()
        initViews()
    }

    private fun setDestination() {
        if (authorizationHelper.isFirstTimeLaunch)
            navigateToOnBoarding()
        else if (authorizationHelper.isLogged)
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
