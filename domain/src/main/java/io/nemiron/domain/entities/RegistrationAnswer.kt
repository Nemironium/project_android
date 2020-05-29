package io.nemiron.domain.entities

data class RegistrationAnswer(
    var isUsernameUnique: Boolean = true,
    var isEmailUnique: Boolean = true,
    var isSuccessful: Boolean = false,
    var error: CommonError = CommonError.NONE
)
