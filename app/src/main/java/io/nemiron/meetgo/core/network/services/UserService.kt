package io.nemiron.meetgo.core.network.services

import io.nemiron.meetgo.core.entities.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("profiles/{id}/")
    suspend fun getUserInfo(@Path("id") userId: Int): Call<UserInfo>
}