package io.nemiron.domain.entities

data class RegistrationAnswer(
    var isUsernameUnique: Boolean = true,
    var isEmailUnique: Boolean = true,
    var isSuccessful: Boolean = false,
    var isNetworkError: Boolean = false,
    var isServerError: Boolean = false,
    var isUnexpectedError: Boolean = false
)
