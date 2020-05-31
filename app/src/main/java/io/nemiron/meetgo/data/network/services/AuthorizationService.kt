package io.nemiron.meetgo.data.network.services

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.meetgo.data.requests.RegistrationRequest
import io.nemiron.meetgo.data.responses.ErrorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST("registration")
    suspend fun register(@Body registrationRequest: RegistrationRequest): NetworkResponse<Unit, ErrorResponse>

    @POST("login")
    suspend fun login(@Body loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse>
}