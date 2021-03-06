package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationDataState
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.domain.extensions.isValidEmail
import io.nemiron.domain.extensions.isValidPassword
import io.nemiron.domain.extensions.isValidUsername


class ValidateRegistrationDataUseCase {
    operator fun invoke(registrationInfo: RegistrationInfo) =
        RegistrationDataState(
            registrationInfo.firstName.isNotBlank(),
            registrationInfo.lastName.isNotBlank(),
            registrationInfo.gender != Gender.BLANK,
            registrationInfo.username.isValidUsername(),
            registrationInfo.email.isValidEmail(),
            registrationInfo.password.isValidPassword(),
            registrationInfo.password == registrationInfo.confirmPassword && registrationInfo.password.isValidPassword()
        )
}
