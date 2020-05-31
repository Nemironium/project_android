package io.nemiron.domain.entities

data class LoginAnswer(
    var isUsernameCorrect: Boolean = true,
    var isPasswordCorrect: Boolean = true,
    var isSuccessful: Boolean = false,
    var error: CommonError = CommonError.NONE
)