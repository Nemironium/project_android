package io.nemiron.meetgo.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    @SerialName("email") val email: String? = null,
    @SerialName("userId") val userId: String? = null,
    @SerialName("password") val password: String
)
