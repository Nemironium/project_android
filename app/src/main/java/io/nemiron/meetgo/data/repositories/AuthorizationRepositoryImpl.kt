package io.nemiron.meetgo.data.repositories

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.apiclients.AuthorizationClient
import io.nemiron.meetgo.data.responses.ErrorResponse

class AuthorizationRepositoryImpl(
    private val authorizationClient: AuthorizationClient
) : AuthorizationRepository {

    override suspend fun registerUser(registrationInfo: RegistrationInfo): NetworkResponse<Unit, ErrorResponse> =
        authorizationClient.registerUser(registrationInfo)

    override suspend fun loginUser(loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse> =
        authorizationClient.loginUser(loginInfo)
}
