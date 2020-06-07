package io.nemiron.meetgo.data.repositories

import io.nemiron.domain.Either
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.Tag
import io.nemiron.meetgo.data.apiclients.TagsClient
import io.nemiron.meetgo.data.managers.AuthorizationManager
import io.nemiron.meetgo.data.responses.TagsResponse
import io.nemiron.meetgo.data.responses.UserTagsResponse
import io.nemiron.meetgo.extensions.handle


class TagsRepository(
    private val tagsClient: TagsClient,
    private val authorizationManager: AuthorizationManager
) {
    suspend fun getAllTags(): Either<CommonError, TagsResponse> {
        return tagsClient.getAllTags().handle()
    }

    suspend fun addTags(tags: List<Tag>): Either<CommonError, Unit> {
        return tagsClient.addTags(tags).handle()
    }

    suspend fun getUserTags(): Either<CommonError, UserTagsResponse> {
        return tagsClient.getUserTags(authorizationManager.userId.orEmpty()).handle()
    }
}
