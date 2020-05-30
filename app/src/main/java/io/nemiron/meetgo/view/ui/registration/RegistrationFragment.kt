package io.nemiron.meetgo.view.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.CommonError.*
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding
import io.nemiron.meetgo.extensions.*
import io.nemiron.meetgo.view.states.RegistrationScreenState
import io.nemiron.meetgo.view.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.state.observe(viewLifecycleOwner, Observer { it?.let {
            handleState(it)
        } })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showError(it)
        })
    }

    private fun initViews() = with(binding) {
        setGenderDropdownAdapter()
        firstNameField.doAfterTextChanged { viewModel.firstName = it?.toString() ?: "" }
        lastNameField.doAfterTextChanged { viewModel.lastName = it?.toString() ?: "" }
        genderField.doAfterTextChanged { viewModel.gender = it?.toString() ?: "" }
        usernameField.doAfterTextChanged { viewModel.username = it?.toString() ?: "" }
        emailField.doAfterTextChanged { viewModel.email = it?.toString() ?: "" }
        passwordField.doAfterTextChanged { viewModel.password = it?.toString() ?: "" }
        confirmPasswordField.doAfterTextChanged { viewModel.confirmPassword = it?.toString() ?: "" }
        registerButton.setOnClickListener { onRegisterButtonClicked() }
        loginButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setGenderDropdownAdapter() = with(binding.genderField) {
        setAdapter(
            ArrayAdapter(
                context,
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.gender_array)
            )
        )
    }

    private fun handleState(state: RegistrationScreenState) {
        if (state.isSuccessRegistration) {
            findNavController().navigate(R.id.action_registration_to_changePartner)
            return
        } else {
            enableViews()
        }
        processUsernameError(state.isUsernameError)
        processUsernameHighlighting(state.isUsernameHighlighted)
        processEmailError(state.isEmailError)
        processEmailHighlighting(state.isEmailHighlighted)
        processPasswordField(state.isPasswordHighlighted)
        processConfirmPasswordField(state.isPasswordConfirmed)
        processProgressBar(state.isProgressIndicated)
        processRegisterButton(state.isRegisterButtonEnabled)
    }

    private fun processUsernameError(mode: Boolean) {
        binding.usernameLayout.error = if (mode)
            getString(R.string.existing_username_error)
        else
            null
    }

    private fun processUsernameHighlighting(mode: Boolean) = with(binding.usernameLayout) {
        if (mode) {
            isCounterEnabled = true
            setErrorColor()
        } else {
            isCounterEnabled = false
            setDefaultColor()
        }
    }

    private fun processEmailError(mode: Boolean) {
        binding.emailLayout.error = if (mode)
            getString(R.string.existing_email_error)
        else
            null
    }

    private fun processEmailHighlighting(mode: Boolean) = if (mode)
        binding.emailLayout.setErrorColor()
    else
        binding.emailLayout.setDefaultColor()


    private fun processPasswordField(mode: Boolean) = with(binding.passwordLayout) {
        if (mode) {
            isCounterEnabled = false
            setAcceptColor()
        } else {
            isCounterEnabled = true
            setDefaultColor()
        }
    }

    private fun processConfirmPasswordField(mode: Boolean) = with(binding.confirmPasswordLayout) {
        if (mode) {
            endIconMode = END_ICON_CUSTOM
            endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_check_circle_accept_24dp)
            setEndIconContentDescription(R.string.check_password_icon)
            setAcceptColor()
        } else {
            endIconMode = END_ICON_PASSWORD_TOGGLE
            setDefaultColor()
        }
    }

    private fun processProgressBar(mode: Boolean) = if (mode)
        binding.registrationProgressBar.show()
    else
        binding.registrationProgressBar.hide()

    private fun processRegisterButton(mode: Boolean) { binding.registerButton.isEnabled = mode == true }

    private fun onRegisterButtonClicked() {
        requireActivity().clearFocus(view)
        disableViews()
        viewModel.submitRegistration()
    }

    private fun disableViews() = with(binding) {
        layout.disableElements()
        registerButton.invisible()
        registrationProgressBar.show()
    }

    private fun enableViews() = with(binding) {
        layout.enableElements()
        registerButton.show()
        registrationProgressBar.hide()
    }

    /*
    * TODO(move network errors to another layer)
    * */
    private fun showError(error: CommonError) {
        when(error) {
            NETWORK_UNAVAILABLE -> showSnackBar(R.string.no_network_error)
            SERVER_ERROR -> showSnackBar(R.string.server_error)
            UNEXPECTED_ERROR -> showSnackBar(R.string.unexpected_error)
            SERVER_UNAVAILABLE -> showSnackBar(R.string.server_unavailable)
            NONE -> return
        }
    }

    private fun showSnackBar(resId: Int) {
        Snackbar.make(binding.root, resId, Snackbar.LENGTH_SHORT).show()
    }
}
