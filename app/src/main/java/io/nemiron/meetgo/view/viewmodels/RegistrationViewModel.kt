package io.nemiron.meetgo.view.viewmodels

import androidx.lifecycle.viewModelScope
import io.nemiron.usecase.RegisterUserUseCase
import io.nemiron.usecase.ValidateRegistrationDataUseCase

class RegistrationViewModel(
    private val validateRegistrationDataUseCase: ValidateRegistrationDataUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : CoroutineViewModel() {

    init {
        viewModelScope
    }


}
