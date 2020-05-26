package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.domain.entities.RegistrationInfo

interface RegisterUserUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo)
}