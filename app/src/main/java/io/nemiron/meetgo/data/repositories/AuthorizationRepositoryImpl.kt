package io.nemiron.meetgo.data.repositories

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.apiclients.AuthorizationClient
import io.nemiron.meetgo.data.managers.AuthorizationManager
import io.nemiron.meetgo.data.responses.ErrorResponse

class AuthorizationRepositoryImpl(
    private val authorizationClient: AuthorizationClient,
    private val authorizationManager: AuthorizationManager
) : AuthorizationRepository {

    override val isFirstTimeLaunch: Boolean
        get() = authorizationManager.isFirstTimeLaunch

    override val isUserLogged: Boolean
        get() = authorizationManager.isLogged

    override suspend fun registerUser(registrationInfo: RegistrationInfo): NetworkResponse<Unit, ErrorResponse> {
        val result = authorizationClient.registerUser(registrationInfo)
        saveCredentials(result)
        return result
    }

    override suspend fun loginUser(loginInfo: LoginInfo): NetworkResponse<Unit, ErrorResponse> {
        val result = authorizationClient.loginUser(loginInfo)
        saveCredentials(result)
        return result
    }

    private fun saveCredentials(response: NetworkResponse<Unit, ErrorResponse>) {
        if (response is NetworkResponse.Success) {
            authorizationManager.saveUserCredentials(
                response.headers?.get("userId"),
                response.headers?.get("userId")
            )
        }
    }
}
