package io.nemiron.meetgo.view.ui.registration

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding
import io.nemiron.meetgo.extensions.clearFocus
import io.nemiron.meetgo.extensions.hide
import io.nemiron.meetgo.extensions.show
import io.nemiron.meetgo.view.states.RegistrationScreenState
import io.nemiron.meetgo.view.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()

    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.state.observe(viewLifecycleOwner, Observer { handleState(it) })
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
        registerButton.setOnClickListener { requireActivity().clearFocus(view); viewModel.submitRegistration() }
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

    private fun handleState(state: RegistrationScreenState?) {
        if (state?.isSuccessRegistration == true) {
            findNavController().navigate(R.id.action_registration_to_changePartner)
            return
        }
        processUsernameError(state?.isUsernameError)
        processUsernameHighlighting(state?.isUsernameHighlighted)
        processEmailError(state?.isEmailError)
        processEmailHighlighting(state?.isEmailHighlighted)
        processPasswordField(state?.isPasswordHighlighted)
        processConfirmPasswordField(state?.isPasswordConfirmed)
        processProgressBar(state?.isProgressIndicated)
        processRegisterButton(state?.isRegisterButtonEnabled)
    }

    private fun processUsernameError(mode: Boolean?) {
        binding.usernameLayout.error = if (mode == true)
            getString(R.string.existing_username_error)
        else
            null
    }

    private fun processUsernameHighlighting(mode: Boolean?) = with(binding.usernameLayout) {
        if (mode == true) {
            isCounterEnabled = true
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorError))
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
        } else {
            isCounterEnabled = false
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
        }
    }

    private fun processEmailError(mode: Boolean?) {
        binding.emailLayout.error = if (mode == true)
            getString(R.string.existing_username_error)
        else
            null
    }

    private fun processEmailHighlighting(mode: Boolean?) = with(binding.emailLayout) {
        defaultHintTextColor = if (mode == true) {
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorError))
        } else {
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
        }
    }

    private fun processPasswordField(mode: Boolean?) = with(binding.passwordLayout) {
        if (mode == true) {
            isCounterEnabled = false
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccept))
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_accept_input_box_stroke)!!)
        } else {
            isCounterEnabled = true
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
        }
    }

    private fun processConfirmPasswordField(mode: Boolean?) = with(binding.confirmPasswordLayout) {
        if (mode == true) {
            endIconMode = END_ICON_CUSTOM
            endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_check_circle_accept_24dp)
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccept))
            setEndIconContentDescription(R.string.check_password_icon)
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_accept_input_box_stroke)!!)
        } else {
            endIconMode = END_ICON_PASSWORD_TOGGLE
            defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
            setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
        }
    }

    private fun processProgressBar(mode: Boolean?) = with(binding.registrationProgressBar) {
        if (mode == true)
            show()
        else
            hide()
    }

    private fun processRegisterButton(mode: Boolean?) {
        binding.registerButton.isEnabled = mode == true
    }
}
