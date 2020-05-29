package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.RegistrationDataState
import io.nemiron.domain.entities.RegistrationInfo


interface ValidateRegistrationDataUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo): RegistrationDataState
}
