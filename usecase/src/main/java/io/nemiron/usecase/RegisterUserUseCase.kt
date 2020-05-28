package io.nemiron.usecase

import io.nemiron.domain.entities.RegistrationAnswer
import io.nemiron.usecase.entities.RegistrationData

interface RegisterUserUseCase {
    operator fun invoke(registrationData: RegistrationData): RegistrationAnswer
}