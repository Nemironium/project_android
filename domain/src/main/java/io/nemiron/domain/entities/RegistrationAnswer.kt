package io.nemiron.domain.entities

data class RegistrationAnswer(
    val isUsernameUnique: Boolean,
    val isEmailUnique: Boolean,
    val isSuccessful: Boolean,
    val isNetworkError: Boolean,
    val isServerError: Boolean
)
