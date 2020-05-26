package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.domain.entities.RegistrationInfo
import io.nemiron.meetgo.domain.entities.RegistrationStatus

interface ValidateRegistrationDataUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo): RegistrationStatus
}