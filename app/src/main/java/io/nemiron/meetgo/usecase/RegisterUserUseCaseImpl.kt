package io.nemiron.meetgo.usecase

import io.nemiron.data.repositories.AuthorizationRepository
import io.nemiron.domain.entities.RegistrationInfo
import io.nemiron.usecase.RegisterUserUseCase

class RegisterUserUseCaseImpl(
    private val repository: AuthorizationRepository
) : RegisterUserUseCase {

    override fun invoke(registrationInfo: RegistrationInfo) {
        TODO("Should invoke repository and return correct status from server")
    }
}
