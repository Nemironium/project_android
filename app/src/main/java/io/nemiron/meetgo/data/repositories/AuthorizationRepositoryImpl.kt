package io.nemiron.meetgo.data.repositories

import io.nemiron.meetgo.data.apiclients.AuthorizationClient
import io.nemiron.meetgo.domain.entities.LoginInfo
import io.nemiron.meetgo.domain.entities.RegistrationInfo

class AuthorizationRepositoryImpl(
    private val authorizationClient: AuthorizationClient
) : AuthorizationRepository {

    override suspend fun registerUser(registrationInfo: RegistrationInfo) {
        TODO("Should invoke authorizationService.register and return result")

    }

    override suspend fun loginUser(loginInfo: LoginInfo) {
        TODO("Should invoke authorizationService.login and return result")
    }

}

