package io.nemiron.meetgo.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    @SerialName("email") val email: String? = null,
    @SerialName("userId") val username: String? = null,
    @SerialName("password") val password: String
)
