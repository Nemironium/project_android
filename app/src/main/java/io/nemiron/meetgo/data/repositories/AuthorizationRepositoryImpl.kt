package io.nemiron.meetgo.data.repositories

import io.nemiron.data.apiclients.AuthorizationClient
import io.nemiron.data.repositories.AuthorizationRepository
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo

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

