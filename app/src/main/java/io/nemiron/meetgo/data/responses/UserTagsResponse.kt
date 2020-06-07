package io.nemiron.meetgo.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserTagsResponse(
    @SerialName("element") val userTags: List<UserTag>
)

@Serializable
data class UserTag(
    @SerialName("tagName") val name: String,

    @SerialName("rating") val rating: Int
)
