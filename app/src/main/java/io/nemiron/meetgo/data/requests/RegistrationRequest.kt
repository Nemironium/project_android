package io.nemiron.meetgo.data.requests

import io.nemiron.domain.entities.Gender
import io.nemiron.domain.entities.RegistrationInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest (
    @SerialName("UserInfo") val userInfo: UserInfo,
    @SerialName("UserPrivate") val userPrivate: UserPrivate
) {
    companion object {
        fun fromRegistrationInfo(info: RegistrationInfo): RegistrationRequest {
            return RegistrationRequest(
                UserInfo(
                    info.firstName,
                    info.lastName,
                    info.gender
                ),
                UserPrivate(
                    info.email,
                    info.username,
                    info.password
                )
            )
        }
    }
}

@Serializable
data class UserInfo (
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("gender") val gender: Gender
)

@Serializable
data class UserPrivate (
    @SerialName("email") val email: String,
    @SerialName("userId") val username: String,
    @SerialName("password") val password: String
)
