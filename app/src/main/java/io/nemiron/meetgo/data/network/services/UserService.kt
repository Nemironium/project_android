package io.nemiron.meetgo.data.network.services

import io.nemiron.meetgo.data.requests.RegistrationRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("profiles/{id}")
    suspend fun getUserInfo(@Path("id") userId: Int): Call<RegistrationRequest>
}