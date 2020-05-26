package io.nemiron.data.apiclients

import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo

interface AuthorizationClient {
    suspend fun registerUser(registrationInfo: RegistrationInfo)
    suspend fun loginUser(loginInfo: LoginInfo)
}
