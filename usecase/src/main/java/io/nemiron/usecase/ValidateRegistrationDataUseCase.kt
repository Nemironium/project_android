package io.nemiron.usecase

import io.nemiron.domain.entities.RegistrationDataState
import io.nemiron.usecase.entities.RegistrationData


interface ValidateRegistrationDataUseCase {
    operator fun invoke(registrationData: RegistrationData): RegistrationDataState
}
