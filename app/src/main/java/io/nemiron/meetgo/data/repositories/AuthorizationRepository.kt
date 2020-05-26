package io.nemiron.meetgo.data.repositories

import io.nemiron.meetgo.domain.entities.LoginInfo
import io.nemiron.meetgo.domain.entities.RegistrationInfo

interface AuthorizationRepository {
    suspend fun registerUser(registrationInfo: RegistrationInfo)
    suspend fun loginUser(loginInfo: LoginInfo)
}
