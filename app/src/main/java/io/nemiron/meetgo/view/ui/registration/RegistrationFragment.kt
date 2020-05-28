package io.nemiron.meetgo.view.ui.registration

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding
import io.nemiron.meetgo.extensions.clearFocus
import io.nemiron.meetgo.network.AuthorizationHelper
import io.nemiron.meetgo.view.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationFragment(private val authorizationHelper: AuthorizationHelper) : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()

    private val registrationViewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGenderDropdownAdapter()


        /*binding.loginButton.setOnClickListener {
            clearScreenFocus()
            with(binding) {
                val data = RegistrationInfo(
                    firstNameField.text.toString(),
                    lastNameField.text.toString(),
                    Gender.getGender(genderField.text.toString()),
                    emailField.text.toString(),
                    usernameField.text.toString(),
                    passwordField.text.toString(),
                    confirmPasswordField.text.toString()
                )
                Timber.d(data.toString())
            }
        }*/

        binding.registerButton.setOnClickListener {
            /*authorizationHelper.sessionId = "test"
            authorizationHelper.userId = "test"*/
            findNavController().navigate(R.id.action_registration_to_changePartner)
        }

        /*
        * Чтобы сделать нормальное показ ошибки для поля пароля.
        * Нужно:
        *       1. включить app:errorEnabled="true" (если не включать, то оно должно автоматом ставиться
        *           и место под поледм должно расширяться) TODO(проверить это)
        *       2. выставить ошибку passwordLayout.error = "error"
        *       3. чтобы убрать ошибку, нужно сделать passwordLayout.error = null
        * */
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

    /*работает */
    private fun showPasswordConfirmed() = with(binding.confirmPasswordLayout) {
        endIconMode = END_ICON_CUSTOM
        endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_check_circle_accept_24dp)
        endIconContentDescription = getString(R.string.check_password_icon)
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
    }

    /*работает */
    private fun showPasswordNotConfirmedError() = with(binding.confirmPasswordLayout) {
        endIconMode = END_ICON_PASSWORD_TOGGLE
        endIconContentDescription = null
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
    }

    /*работает */
    private fun showIncorrectUsernameError() = with(binding.usernameLayout) {
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
        defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorError))
    }

    /*работает, но не тот цвет, когда нет данных */
    private fun showCorrectUsername() = with(binding.usernameLayout) {
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
        defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /*работает */
    private fun showIncorrectPasswordError() = with(binding.passwordLayout) {
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_invalid_input_box_stroke)!!)
        defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorError))
    }

    /*работает, но не тот цвет, когда нет данных */
    private fun showCorrectPassword() = with(binding.passwordLayout) {
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_accept_input_box_stroke)!!)
        defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccept))
    }

    /* вызывать, когда в поле пароль 0 символов*/
    private fun showDefaultPassword() = with(binding.passwordLayout) {
        setBoxStrokeColorStateList(ContextCompat.getColorStateList(context, R.color.text_default_input_box_stroke)!!)
        defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    private fun showExistingUsernameError()   {
        binding.usernameLayout.error = getString(R.string.existing_username_error)
    }

    private fun cleanUsernameError() { binding.usernameLayout.error = null }

    private fun showExistingEmailError() {
        binding.usernameLayout.error = getString(R.string.existing_email_error)
    }

    private fun cleanEmailError() { binding.usernameLayout.error = null }

    private fun clearScreenFocus() = requireActivity().clearFocus(view)
}
