package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.domain.entities.Gender
import io.nemiron.meetgo.domain.entities.RegistrationInfo
import io.nemiron.meetgo.domain.entities.RegistrationStatus
import io.nemiron.meetgo.domain.entities.RegistrationStatus.*
import io.nemiron.meetgo.extensions.isValidEmail
import io.nemiron.meetgo.extensions.isValidPassword
import io.nemiron.meetgo.extensions.isValidUsername


class ValidateRegistrationDataUseCaseImpl : ValidateRegistrationDataUseCase {
    override fun invoke(registrationInfo: RegistrationInfo): RegistrationStatus {
        return when {
            registrationInfo.firstName.isBlank() -> EMPTY_FIRST_NAME
            registrationInfo.lastName.isBlank() -> EMPTY_LAST_NAME
            registrationInfo.gender == Gender.BLANK -> EMPTY_GENDER
            registrationInfo.username.isBlank() -> EMPTY_USERNAME
            !registrationInfo.username.isValidUsername() -> INCORRECT_USERNAME
            registrationInfo.email.isBlank() -> EMPTY_EMAIL
            !registrationInfo.email.isValidEmail() -> INCORRECT_EMAIL
            registrationInfo.password.isBlank() -> EMPTY_PASSWORD
            !registrationInfo.password.isValidPassword() -> INCORRECT_PASSWORD
            registrationInfo.confirmPassword.isBlank() -> EMPTY_CONFIRM_PASSWORD
            registrationInfo.confirmPassword != registrationInfo.password -> NOT_CONFIRMED_PASSWORD
            else -> VALIDATED
        }
    }
}