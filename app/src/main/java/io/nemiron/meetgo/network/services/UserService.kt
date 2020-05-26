package io.nemiron.meetgo.network.services

import io.nemiron.meetgo.domain.entities.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("profiles/{id}/")
    suspend fun getUserInfo(@Path("id") userId: Int): Call<UserInfo>
}