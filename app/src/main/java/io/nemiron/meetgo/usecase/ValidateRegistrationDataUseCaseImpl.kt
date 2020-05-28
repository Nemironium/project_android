package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationDataState
import io.nemiron.domain.entities.extensions.isValidEmail
import io.nemiron.domain.entities.extensions.isValidPassword
import io.nemiron.domain.entities.extensions.isValidUsername
import io.nemiron.usecase.ValidateRegistrationDataUseCase
import io.nemiron.usecase.entities.RegistrationData


class ValidateRegistrationDataUseCaseImpl : ValidateRegistrationDataUseCase {
    override fun invoke(registrationData: RegistrationData) =
        RegistrationDataState(
            registrationData.firstName.isNotBlank(),
            registrationData.lastName.isNotBlank(),
            Gender.getGender(registrationData.gender) != Gender.BLANK,
            registrationData.username.isValidUsername(),
            registrationData.email.isValidEmail(),
            registrationData.password.isValidPassword(),
            registrationData.password == registrationData.confirmPassword && registrationData.password.isValidPassword()
        )
}
