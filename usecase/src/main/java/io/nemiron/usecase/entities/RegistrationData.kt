package io.nemiron.usecase.entities

data class RegistrationData (
    val firstName: String,
     val lastName: String,
     val gender: String,
     val username: String,
     val email: String,
     val password: String,
     val confirmPassword: String = ""
)