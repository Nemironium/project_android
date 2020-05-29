package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.RegistrationAnswer
import io.nemiron.domain.entities.RegistrationInfo

interface RegisterUserUseCase {
    suspend operator fun invoke(
        registrationInfo: RegistrationInfo
    ): RegistrationAnswer
}