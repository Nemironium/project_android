package io.nemiron.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error (
    @SerialName("message") val message: String,
    @SerialName("code") val code: String
)