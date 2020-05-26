package io.nemiron.data.repositories

import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo

interface AuthorizationRepository {
    suspend fun registerUser(registrationInfo: RegistrationInfo)
    suspend fun loginUser(loginInfo: LoginInfo)
}
