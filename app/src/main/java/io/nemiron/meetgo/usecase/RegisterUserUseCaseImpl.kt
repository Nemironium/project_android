package io.nemiron.meetgo.usecase

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.RegistrationAnswer
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import io.nemiron.meetgo.extensions.ServerError
import io.nemiron.meetgo.extensions.handleServerError

class RegisterUserUseCaseImpl(
    private val repository: AuthorizationRepository
) : RegisterUserUseCase {

    override suspend fun invoke(
        registrationInfo: RegistrationInfo
    ): RegistrationAnswer {

        val registrationAnswer = RegistrationAnswer()
        when(val registration = repository.registerUser(registrationInfo)) {
            is NetworkResponse.Success -> {
                registrationAnswer.isSuccessful = true
            }
            is NetworkResponse.NetworkError ->  {
                registrationAnswer.error = CommonError.getConnectionError(registration.error)
            }
            is NetworkResponse.UnknownError -> {
                registrationAnswer.error = CommonError.UNEXPECTED_ERROR
            }
            is NetworkResponse.ServerError -> when(registration.handleServerError<Unit>()) {
                ServerError.USERNAME_NOT_UNIQUE -> registrationAnswer.isUsernameUnique = false
                ServerError.EMAIL_NOT_UNIQUE -> registrationAnswer.isEmailUnique = false
                else -> registrationAnswer.error = CommonError.SERVER_ERROR
            }
        }
        return registrationAnswer
    }
}
