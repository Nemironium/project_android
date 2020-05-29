package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.usecase.RegisterUserUseCase
import io.nemiron.meetgo.usecase.ValidateRegistrationDataUseCase
import io.nemiron.meetgo.view.states.RegistrationScreenState
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val validateUseCase: ValidateRegistrationDataUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<RegistrationScreenState>()

    val state: LiveData<RegistrationScreenState>
        get() = _state

    var firstName = ""
        set(value) {
            field = value
            validateForm()
        }

    var lastName = ""
        set(value) {
            field = value
            validateForm()
        }

    var gender = ""
        set(value) {
            field = value
            validateForm()
        }

    var username = ""
        set(value) {
            field = value
            validateForm()
        }

    var email = ""
        set(value) {
            field = value
            validateForm()
        }

    var password = ""
        set(value) {
            field = value
            validateForm()
        }

    var confirmPassword = ""
        set(value) {
            field = value
            validateForm()
        }

    fun submitRegistration() {
        viewModelScope.launch {
            val registration = registerUserUseCase(
                RegistrationInfo(
                    firstName,
                    lastName,
                    Gender.getGender(gender),
                    username,
                    email,
                    password
                )
            )
            _state.postValue(RegistrationScreenState(
                isUsernameError = !registration.isUsernameUnique,
                isEmailError = !registration.isEmailUnique,
                isSuccessRegistration = registration.isSuccessful,
                isProgressIndicated = false
            ))
        }
    }

    private fun validateForm() {
        val validation = validateUseCase(
            RegistrationInfo(
                firstName,
                lastName,
                Gender.getGender(gender),
                username,
                email,
                password,
                confirmPassword
            )
        )

        _state.postValue(RegistrationScreenState(
            isUsernameHighlighted = !validation.isUsernameCorrect,
            isEmailHighlighted = !validation.isEmailCorrect,
            isPasswordHighlighted = validation.isPasswordStrong,
            isPasswordConfirmed = validation.isPasswordConfirmed,
            isRegisterButtonEnabled = validation.isAllValid
        ))
    }
}
