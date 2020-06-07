package io.nemiron.meetgo.data.apiclients

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.Tag
import io.nemiron.meetgo.data.responses.ErrorResponse
import io.nemiron.meetgo.data.responses.TagsResponse
import io.nemiron.meetgo.data.responses.UserTagsResponse


interface TagsClient {
    suspend fun getAllTags(): NetworkResponse<TagsResponse, ErrorResponse>
    suspend fun addTags(tags: List<Tag>): NetworkResponse<Unit, ErrorResponse>
    suspend fun getUserTags(userId: String): NetworkResponse<UserTagsResponse, ErrorResponse>
}
