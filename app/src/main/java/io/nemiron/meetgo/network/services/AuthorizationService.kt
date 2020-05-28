package io.nemiron.meetgo.network.services

import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.responses.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST("registration/")
    suspend fun register(@Body registrationInfo: RegistrationInfo): AuthResponse

    @POST("login/")
    suspend fun login(@Body loginInfo: LoginInfo): AuthResponse
}