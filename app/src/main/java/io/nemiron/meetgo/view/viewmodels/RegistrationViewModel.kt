package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.view.states.RegistrationScreenState
import io.nemiron.usecase.RegisterUserUseCase
import io.nemiron.usecase.ValidateRegistrationDataUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

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

/*
    fun setUsernameError(`val`: Boolean) { _state.value?.isUsernameError = `val` }

    fun setUsernameHighlighted(`val`: Boolean) { _state.value?.isUsernameHighlighted = `val` }

    fun setEmailError(`val`: Boolean) { _state.value?.isEmailError = `val` }

    fun setEmailHighlighted(`val`: Boolean) { _state.value?.isEmailHighlighted = `val` }

    fun setPasswordHighlighted(`val`: Boolean) { _state.value?.isPasswordHighlighted = `val` }

    fun setPasswordConfirmed(`val`: Boolean) { _state.value?.isPasswordConfirmed = `val` }

    fun setProgressIndicator(`val`: Boolean) { _state.value?.isProgressIndicated = `val` }

    fun setRegisterButtonEnabled(`val`: Boolean) { _state.value?.isRegisterButtonEnabled = `val` }

    fun setRegistrationSuccess(`val`: Boolean) { _state.value?.isSuccessRegistration = `val` }
*/

    fun submitRegistration() {
        viewModelScope.launch {
            registerUserUseCase(RegistrationInfo(
                firstName,
                lastName,
                Gender.getGender(gender),
                username,
                email,
                password,
                confirmPassword
            ))
        }
    }

    private fun validateForm() {
        val validation = validateUseCase(RegistrationInfo(
            firstName,
            lastName,
            Gender.getGender(gender),
            username,
            email,
            password,
            confirmPassword
        ))

        Timber.d("validateUseCase: $validation")
        Timber.d("validation.isAllValid: ${validation.isAllValid}")

        _state.postValue(RegistrationScreenState(
            isUsernameHighlighted = !validation.isUsernameCorrect,
            isEmailHighlighted = !validation.isEmailCorrect,
            isPasswordHighlighted = validation.isPasswordStrong,
            isPasswordConfirmed = validation.isPasswordConfirmed,
            isRegisterButtonEnabled = validation.isAllValid
        ))

        Timber.d("RegistrationScreenState: ${_state.value}")

    }
}
