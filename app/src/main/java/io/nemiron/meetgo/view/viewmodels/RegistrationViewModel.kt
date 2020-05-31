package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.*
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.extensions.SingleLiveEvent
import io.nemiron.meetgo.usecase.RegisterUseCase
import io.nemiron.meetgo.usecase.ValidateRegistrationDataUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val validateUseCase: ValidateRegistrationDataUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    data class RegistrationScreenState(
        /* состояние когда username не уникальный (нужен ответ от сервера) */
        val isUsernameError: Boolean = false,
        /* состояние, когда username не валидный */
        val isUsernameHighlighted: Boolean = false,
        /* состояние, когда email не уникальный (нужен ответ от сервера) */
        val isEmailError: Boolean = false,
        /* состояние, когда email не валидный */
        val isEmailHighlighted: Boolean = false,
        /* состояние, когда password ВАЛИДНЫЙ */
        val isPasswordHighlighted: Boolean = false,
        /* состояние, когда пароли совпадают */
        val isPasswordConfirmed: Boolean = false,
        /* состояние, когда идёт загрузка данных */
        val isProgressIndicated: Boolean = false,
        /* состояние, когда все данные на экране валидные */
        val isRegisterButtonEnabled: Boolean = false
    )

    enum class RegistrationNavigation {
        TO_TAGS,
        TO_LOGIN
    }

    private val _state = MutableLiveData<RegistrationScreenState>()
    private val _errorMessage = SingleLiveEvent<CommonError>()
    private val _navigateTo = SingleLiveEvent<RegistrationNavigation>()

    val state: LiveData<RegistrationScreenState>
        get() = _state.distinctUntilChanged()

    val errorMessage: LiveData<CommonError>
        get() = _errorMessage

    val navigateTo: LiveData<RegistrationNavigation>
        get() = _navigateTo

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

    fun submitRegistration() {
        _state.value = _state.value?.copy(isProgressIndicated = true)
        registerAsync()
    }

    fun toLogin() {
        _navigateTo.value = RegistrationNavigation.TO_LOGIN
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

    private fun registerAsync() = viewModelScope.launch {
        with(registerUseCase(registrationInfo)) {
            _state.postValue(_state.value?.copy(
                isUsernameError = !isUsernameUnique,
                isEmailError = !isEmailUnique,
                isProgressIndicated = false
            ))
            if (error != CommonError.NONE)
                _errorMessage.postValue(error)
            if (isSuccessful)
                _navigateTo.postValue(RegistrationNavigation.TO_TAGS)
        }
    }
}
