package io.nemiron.usecase

import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.domain.entities.RegistrationStatus

interface ValidateRegistrationDataUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo): RegistrationStatus
}
