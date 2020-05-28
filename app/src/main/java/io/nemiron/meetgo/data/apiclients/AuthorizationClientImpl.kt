package io.nemiron.meetgo.data.apiclients

import io.nemiron.data.apiclients.AuthorizationClient
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.network.services.AuthorizationService

class AuthorizationClientImpl(private val authorizationService: AuthorizationService) :
    AuthorizationClient {
    override suspend fun registerUser(registrationInfo: RegistrationInfo) {

        val response = authorizationService.register(registrationInfo)



    }

    override suspend fun loginUser(loginInfo: LoginInfo) {
        TODO("Should invoke authorizationService.login and return result")
    }
}
