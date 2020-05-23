package io.nemiron.meetgo.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationInfo(
    @SerialName("email") val email: String,
    @SerialName("userId") val userId: String,
    @SerialName("password") val password: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("gender") val gender: Gender
)