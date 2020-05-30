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
    private val _navigateToNextScreen = SingleLiveEvent<Any>()

    val state: LiveData<RegistrationScreenState>
        get() = _state.distinctUntilChanged()

    val errorMessage: LiveData<CommonError>
        get() = _errorMessage

    val navigateToNextScreen: LiveData<Any>
        get() = _navigateToNextScreen

    private val registrationInfo: RegistrationInfo
        get() = RegistrationInfo(firstName, lastName, Gender.getGender(gender),
            username, email, password, confirmPassword)

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

    private fun validateForm() = with(validateUseCase(registrationInfo)) {
        _state.postValue(RegistrationScreenState().copy(
            isUsernameHighlighted = !isUsernameCorrect,
            isEmailHighlighted = !isEmailCorrect,
            isPasswordHighlighted = isPasswordStrong,
            isPasswordConfirmed = isPasswordConfirmed,
            isRegisterButtonEnabled = isAllValid
        ))
    }

    fun submitRegistration() {
        _state.value = _state.value?.copy(isProgressIndicated = true)
        registerAsync()
    }

    private fun registerAsync() = viewModelScope.launch {
        with(registerUserUseCase(registrationInfo)) {
            _state.postValue(_state.value?.copy(
                isUsernameError = !isUsernameUnique,
                isEmailError = !isEmailUnique,
                isProgressIndicated = false
            ))
            if (error != CommonError.NONE)
                _errorMessage.postValue(error)
            if (isSuccessful)
                _navigateToNextScreen.call()
        }
    }
}
