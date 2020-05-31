package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.RegistrationAnswer
import io.nemiron.domain.entities.RegistrationInfo

interface RegisterUseCase {
    suspend operator fun invoke(
        info: RegistrationInfo
    ): RegistrationAnswer
}