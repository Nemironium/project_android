package io.nemiron.usecase

import io.nemiron.domain.entities.RegistrationInfo

interface RegisterUserUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo)
}