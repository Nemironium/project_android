package io.nemiron.meetgo.network.services

import io.nemiron.meetgo.domain.entities.RegistrationInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {
    @POST("registration/")
    suspend fun register(@Body registrationInfo: RegistrationInfo): Call<ResponseBody>

    @POST("login/")
    suspend fun login(@Body registrationInfo: RegistrationInfo): Call<ResponseBody>
}