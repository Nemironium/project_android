package io.nemiron.meetgo.data.apiclients

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.responses.ErrorResponse

interface AuthorizationClient {
    suspend fun registerUser(registrationInfo: RegistrationInfo): NetworkResponse<Unit, ErrorResponse>
    suspend fun loginUser(loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse>
}
