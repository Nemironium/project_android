package io.nemiron.meetgo.interactors

import io.nemiron.meetgo.domain.entities.RegistrationInfo

interface RegisterUserUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo)
}