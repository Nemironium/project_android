package io.nemiron.meetgo.view.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.CommonError.*
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentLoginBinding
import io.nemiron.meetgo.extensions.*
import io.nemiron.meetgo.view.ui.main_activity.MainActivity
import io.nemiron.meetgo.view.viewmodels.LoginViewModel
import io.nemiron.meetgo.view.viewmodels.LoginViewModel.LoginNavigation
import io.nemiron.meetgo.view.viewmodels.LoginViewModel.LoginNavigation.*
import io.nemiron.meetgo.view.viewmodels.LoginViewModel.LoginScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.forgotPassword.setOnClickListener { viewModel.forgotPassword() }
        binding.registrationButton.setOnClickListener { viewModel.registration() }
        binding.loginButton.setOnClickListener { onLoginButtonClicked() }
    }

    private fun initObservers() = with(viewModel) {
        state.observe(viewLifecycleOwner, Observer {
            Timber.d("state.observe: $it")
            it?.let {
            handleState(it)
        }})
        errorMessage.observe(viewLifecycleOwner, Observer { it?.let{
            showError(it)
        }})
        navigateTo.observe(viewLifecycleOwner, Observer { it?.let {
            handleNavigation(it)
        }})
        /*
        * FIXME(этот костыль используется, потому что нельзя это сделать в init-блоке ViewModel.
        *  Надо найти нормальный способ это сделать, ибо ViewModel живёт намного дольше,
        *  чем фрагмент и каждый раз этот метод будет вызываться)
        * */
        viewModel.checkNavigation()
    }

    /*
    * TODO(сделать этот метод общим для всех экранов)
    * */
    private fun showError(error: CommonError) {
        when(error) {
            NETWORK_UNAVAILABLE -> showSnackBar(R.string.no_network_error)
            SERVER_ERROR -> showSnackBar(R.string.server_error)
            UNEXPECTED_ERROR -> showSnackBar(R.string.unexpected_error)
            SERVER_UNAVAILABLE -> showSnackBar(R.string.server_unavailable_error)
            else -> return
        }
    }

    private fun handleState(state: LoginScreenState) {
        Timber.d("handleState: $state")
        processProgressIndicating(state.isProgressIndicated)
        processUsernameError(state.isUsernameError)
        processPasswordError(state.isPasswordError)
    }

    private fun handleNavigation(navigate: LoginNavigation) = when(navigate) {
        TO_HOME -> (activity as MainActivity).setApplicationNavigation()
        TO_REGISTRATION -> findNavController().navigate(R.id.action_login_to_registration)
        TO_ON_BOARDING -> findNavController().navigate(R.id.action_login_to_onBoarding)
        TO_FORGOT_PASSWORD -> findNavController().navigate(R.id.action_login_to_forgotPassword)
    }

    private fun onLoginButtonClicked() {
        requireActivity().clearFocus(view)
        viewModel.submitLogin(
            binding.usernameField.text?.toString() ?: "",
            binding.passwordField.text?.toString() ?: ""
        )
    }

    private fun processProgressIndicating(mode: Boolean) = with(binding) {
        if (mode) {
            loginLayout.disableElements()
            loginButton.invisible()
            loginProgressBar.show()
        } else {
            loginLayout.enableElements()
            loginButton.show()
            loginProgressBar.hide()
        }
    }

    private fun processUsernameError(mode: Boolean) {
        Timber.d("processUsernameError mode: $mode")
        binding.usernameLayout.error = if (mode)
            getString(R.string.username_not_found_error)
        else
            null
    }

    private fun processPasswordError(mode: Boolean) {
        binding.passwordLayout.error = if (mode)
            getString(R.string.wrong_password_error)
        else
            null
    }

    private fun showSnackBar(resId: Int) =
        Snackbar.make(binding.root, resId, Snackbar.LENGTH_SHORT).show()
}
