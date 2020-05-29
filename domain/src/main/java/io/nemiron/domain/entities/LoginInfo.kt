package io.nemiron.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    @SerialName("userId") val username: String,
    @SerialName("password") val password: String
)
