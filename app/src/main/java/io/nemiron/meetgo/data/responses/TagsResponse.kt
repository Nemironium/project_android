package io.nemiron.meetgo.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
    @SerialName("element") val tags: List<TagInfo>
)

@Serializable
data class TagInfo(
    @SerialName("name") val name: String,

    @SerialName("description") val description: String
)