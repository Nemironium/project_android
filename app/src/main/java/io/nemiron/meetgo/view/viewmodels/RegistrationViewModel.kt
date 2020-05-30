package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.*
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.extensions.SingleLiveEvent
import io.nemiron.meetgo.usecase.RegisterUserUseCase
import io.nemiron.meetgo.usecase.ValidateRegistrationDataUseCase
import io.nemiron.meetgo.view.states.RegistrationScreenState
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val validateUseCase: ValidateRegistrationDataUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<RegistrationScreenState>()
    private val _errorMessage = SingleLiveEvent<CommonError>()

    val state: LiveData<RegistrationScreenState>
        get() = _state.distinctUntilChanged()

    val errorMessage: LiveData<CommonError>
        get() = _errorMessage

    /*
    * These vars can be simplified by using data binding.
    * But I think this is so painful to edit XML files
    * */
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

        _state.postValue(RegistrationScreenState().copy(
            isUsernameHighlighted = !validation.isUsernameCorrect,
            isEmailHighlighted = !validation.isEmailCorrect,
            isPasswordHighlighted = validation.isPasswordStrong,
            isPasswordConfirmed = validation.isPasswordConfirmed,
            isRegisterButtonEnabled = validation.isAllValid
        ))
    }

    fun submitRegistration() {
        _state.value = _state.value?.copy(isProgressIndicated = true)
        registerAsync()
    }

    private fun registerAsync() = viewModelScope.launch {
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
        _state.postValue(
            _state.value?.copy(
                isUsernameError = !registration.isUsernameUnique,
                isEmailError = !registration.isEmailUnique,
                isSuccessRegistration = registration.isSuccessful,
                isProgressIndicated = false
            )
        )
        _errorMessage.postValue(registration.error)
    }
}
