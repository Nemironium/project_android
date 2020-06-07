package io.nemiron.meetgo.data.apiclients

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.Tag
import io.nemiron.meetgo.data.network.services.TagsService
import io.nemiron.meetgo.data.responses.ErrorResponse
import io.nemiron.meetgo.data.responses.TagsResponse
import io.nemiron.meetgo.data.responses.UserTagsResponse

class TagsClientImpl(private val tagsService: TagsService) : TagsClient {
    override suspend fun getAllTags(): NetworkResponse<TagsResponse, ErrorResponse> {
        return tagsService.getAllTags()
    }

    override suspend fun addTags(tags: List<Tag>): NetworkResponse<Unit, ErrorResponse> {
        return tagsService.addTags(tags)
    }

    override suspend fun getUserTags(userId: String): NetworkResponse<UserTagsResponse, ErrorResponse> {
        return tagsService.getUserTags(userId)
    }
}
