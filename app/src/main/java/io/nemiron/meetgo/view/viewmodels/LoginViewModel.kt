package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.*
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.meetgo.extensions.SingleLiveEvent
import io.nemiron.meetgo.extensions.default
import io.nemiron.meetgo.usecase.CheckAuthUseCase
import io.nemiron.meetgo.usecase.CheckFirstLaunchUseCase
import io.nemiron.meetgo.usecase.LoginUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

/*
* TODO(найти способ группировать use-case'ы, если их больше 2)
* */
class LoginViewModel(
    private val checkAuthUseCase: CheckAuthUseCase,
    private val checkFirstLaunchUseCase: CheckFirstLaunchUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    data class LoginScreenState(
        val isProgressIndicated: Boolean = false,
        val isUsernameError: Boolean = false,
        val isPasswordError: Boolean = false
    )

    enum class LoginNavigation {
        TO_HOME,
        TO_REGISTRATION,
        TO_ON_BOARDING,
        TO_FORGOT_PASSWORD
    }

    private val initNavigation: LoginNavigation?
        get() = when {
            checkAuthUseCase() -> LoginNavigation.TO_HOME
            checkFirstLaunchUseCase() -> LoginNavigation.TO_ON_BOARDING
            else -> null
        }

    private val _state = MutableLiveData(LoginScreenState())
    private val _errorMessage = SingleLiveEvent<CommonError>()
    private val _navigateTo = SingleLiveEvent<LoginNavigation?>().default(initNavigation)

    val state: LiveData<LoginScreenState>
        get() = _state.distinctUntilChanged()

    val errorMessage: LiveData<CommonError>
        get() = _errorMessage

    val navigateTo: LiveData<LoginNavigation?>
        get() = _navigateTo

    fun checkNavigation() {
        if (checkAuthUseCase())
            _navigateTo.value = LoginNavigation.TO_HOME
        if (checkFirstLaunchUseCase())
            _navigateTo.value = LoginNavigation.TO_ON_BOARDING
    }

    fun submitLogin(username: String, password: String) {
        Timber.d("before submitLogin (_state): ${_state.value}")
        Timber.d("before submitLogin (state): ${state.value}")
        _state.value = _state.value?.copy(isProgressIndicated = true)
        Timber.d("after submitLogin (_state): ${_state.value}")
        Timber.d("after submitLogin (state): ${state.value}")
        loginAsync(LoginInfo(username, password))
    }

    fun forgotPassword() {
        _navigateTo.value = LoginNavigation.TO_FORGOT_PASSWORD
    }

    fun registration() {
        _navigateTo.value = LoginNavigation.TO_REGISTRATION
    }

    private fun loginAsync(loginInfo: LoginInfo) = viewModelScope.launch {
        with(loginUseCase(loginInfo)) {
            _state.postValue(LoginScreenState(
                isUsernameError = !isUsernameCorrect,
                isPasswordError = !isPasswordCorrect,
                isProgressIndicated = false
            ))
            if (error != CommonError.NONE)
                _errorMessage.postValue(error)

            if (isSuccessful)
                _navigateTo.postValue(LoginNavigation.TO_HOME)
        }
    }
}
