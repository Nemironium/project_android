package io.nemiron.meetgo.view.viewmodels

import io.nemiron.meetgo.usecase.RegisterUserUseCase
import io.nemiron.meetgo.usecase.ValidateRegistrationDataUseCase

class RegistrationViewModel(
    private val validateRegistrationDataUseCase: ValidateRegistrationDataUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : CoroutineViewModel() {

}
