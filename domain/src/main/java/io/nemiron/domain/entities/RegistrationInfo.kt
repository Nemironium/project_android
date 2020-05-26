package io.nemiron.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class RegistrationInfo(
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("gender") val gender: Gender,
    @SerialName("userId") val username: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @Transient val confirmPassword: String = ""
)