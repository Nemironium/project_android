package io.nemiron.domain.entities

data class RegistrationInfo(
     val firstName: String,
     val lastName: String,
     val gender: Gender,
     val username: String,
     val email: String,
     val password: String,
     val confirmPassword: String = ""
)