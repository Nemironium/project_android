package io.nemiron.meetgo.model.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    @SerialName("male") MALE,
    @SerialName("female") FEMALE,
    @SerialName("another") ANOTHER
}