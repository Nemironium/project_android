package io.nemiron.meetgo.data.repositories

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.apiclients.AuthorizationClient
import io.nemiron.meetgo.data.managers.AuthorizationManager
import io.nemiron.meetgo.data.responses.ErrorResponse

class AuthorizationRepository(
    private val authorizationClient: AuthorizationClient,
    private val authorizationManager: AuthorizationManager
)  {

    val isFirstTimeLaunch: Boolean
        get() = authorizationManager.isFirstTimeLaunch

    val isUserLogged: Boolean
        get() = authorizationManager.isUserLogged

    suspend fun registerUser(registrationInfo: RegistrationInfo): NetworkResponse<Unit, ErrorResponse> {
        val result = authorizationClient.registerUser(registrationInfo)
        saveCredentials(registrationInfo.username)
        return result
    }

    suspend fun loginUser(loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse> {
        val result = authorizationClient.loginUser(loginInfo)
        saveCredentials(loginInfo.username)
        return result
    }

    private fun saveCredentials(userId: String) {
        authorizationManager.userId = userId
    }
}
