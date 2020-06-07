package io.nemiron.meetgo.data.network.services

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.Tag
import io.nemiron.meetgo.data.responses.ErrorResponse
import io.nemiron.meetgo.data.responses.TagsResponse
import io.nemiron.meetgo.data.responses.UserTagsResponse
import retrofit2.http.*

interface TagsService {
    @GET("userTags")
    suspend fun getAllTags(
        @Query("from") from: Int = 0,
        @Query("to") to: Int = 40
    ): NetworkResponse<TagsResponse, ErrorResponse>

    @POST("userTags/add")
    suspend fun addTags(@Body tags: List<Tag>): NetworkResponse<Unit, ErrorResponse>

    @GET("userTags/get/{userId}")
    suspend fun getUserTags(@Path("userId") userId: String): NetworkResponse<UserTagsResponse, ErrorResponse>
}
