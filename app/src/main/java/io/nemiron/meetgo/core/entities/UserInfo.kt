package io.nemiron.meetgo.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo (
    @SerialName("profileFirstName") var firstName: String,
    @SerialName("profileLastName") var lastName: String,
    @SerialName("profileScreenName") var screenName: String
)