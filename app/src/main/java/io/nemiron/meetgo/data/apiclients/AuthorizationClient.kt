package io.nemiron.meetgo.data.apiclients

import io.nemiron.meetgo.domain.entities.LoginInfo
import io.nemiron.meetgo.domain.entities.RegistrationInfo

interface AuthorizationClient {
    suspend fun registerUser(registrationInfo: RegistrationInfo)
    suspend fun loginUser(loginInfo: LoginInfo)
}
