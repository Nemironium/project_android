package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.nemiron.meetgo.view.states.RegistrationScreenState
import io.nemiron.usecase.RegisterUserUseCase
import io.nemiron.usecase.ValidateRegistrationDataUseCase
import io.nemiron.usecase.entities.RegistrationData
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
            registerUserUseCase(
                RegistrationData(
                    firstName,
                    lastName,
                    gender,
                    username,
                    email,
                    password
                )
            )
        }
    }

    private fun validateForm() {
        val validation = validateUseCase(
            RegistrationData(
                firstName,
                lastName,
                gender,
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
