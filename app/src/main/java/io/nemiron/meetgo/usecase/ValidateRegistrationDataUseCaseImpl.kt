package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationDataState
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.domain.entities.extensions.isValidEmail
import io.nemiron.domain.entities.extensions.isValidPassword
import io.nemiron.domain.entities.extensions.isValidUsername
import io.nemiron.usecase.ValidateRegistrationDataUseCase


class ValidateRegistrationDataUseCaseImpl : ValidateRegistrationDataUseCase {
    override fun invoke(registrationInfo: RegistrationInfo) =
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
