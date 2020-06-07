package io.nemiron.meetgo.usecase

import io.nemiron.domain.Either
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.Tag
import io.nemiron.meetgo.data.repositories.TagsRepository
import io.nemiron.meetgo.data.responses.TagsResponse as Tags
import io.nemiron.meetgo.data.responses.UserTagsResponse as UserTags

class TagsInteractor(private val repository: TagsRepository) {

    suspend fun getAllTags(): Either<CommonError, Tags>
            = repository.getAllTags()

    suspend fun getUserTags(): Either<CommonError, UserTags>
            = repository.getUserTags()

    suspend fun addTags(tags: List<Tag>): Either<CommonError, Unit>
            = repository.addTags(tags)
}
