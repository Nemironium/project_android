package io.nemiron.meetgo.data.apiclients

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.network.services.AuthorizationService
import io.nemiron.meetgo.data.responses.ErrorResponse

class AuthorizationClientImpl(private val authorizationService: AuthorizationService) :
    AuthorizationClient {
    override suspend fun registerUser(registrationInfo: RegistrationInfo): NetworkResponse<Unit, ErrorResponse> {
        return authorizationService.register(registrationInfo)
    }

    override suspend fun loginUser(loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse> {
        TODO("Should invoke authorizationService.login and return result")
    }
}
