package io.nemiron.meetgo.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse (
    @SerialName("error") val error: Error
)

@Serializable
data class Error(
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String
)
