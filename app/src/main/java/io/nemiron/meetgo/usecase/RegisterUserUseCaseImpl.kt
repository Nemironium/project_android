package io.nemiron.meetgo.usecase

import io.nemiron.data.repositories.AuthorizationRepository
import io.nemiron.domain.entities.RegistrationAnswer
import io.nemiron.usecase.RegisterUserUseCase
import io.nemiron.usecase.entities.RegistrationData

class RegisterUserUseCaseImpl(
    private val repository: AuthorizationRepository
) : RegisterUserUseCase {

    override fun invoke(registrationData: RegistrationData): RegistrationAnswer {
        TODO("Should invoke repository and return correct status from server")
    }
}
