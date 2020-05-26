package io.nemiron.meetgo.domain

import io.nemiron.meetgo.domain.RegistrationStatus.*
import io.nemiron.meetgo.domain.entities.Gender
import io.nemiron.meetgo.domain.entities.RegistrationInfo
import io.nemiron.meetgo.extensions.isValidEmail
import io.nemiron.meetgo.extensions.isValidPassword
import io.nemiron.meetgo.extensions.isValidUsername

class ValidateRegistrationData {
    operator fun invoke(info: RegistrationInfo): RegistrationStatus {
        return when {
            info.firstName.isBlank() -> EMPTY_FIRST_NAME
            info.lastName.isBlank() -> EMPTY_LAST_NAME
            info.gender == Gender.BLANK -> EMPTY_GENDER
            info.username.isBlank() -> EMPTY_USERNAME
            !info.username.isValidUsername() -> INCORRECT_USERNAME
            info.email.isBlank() -> EMPTY_EMAIL
            !info.email.isValidEmail() -> INCORRECT_EMAIL
            info.password.isBlank() -> EMPTY_PASSWORD
            !info.password.isValidPassword() -> INCORRECT_PASSWORD
            info.confirmPassword.isBlank() -> EMPTY_CONFIRM_PASSWORD
            info.confirmPassword != info.password -> NOT_CONFIRMED_PASSWORD
            else -> VALIDATED
        }
    }
}