package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import io.nemiron.meetgo.domain.entities.RegistrationInfo

class RegisterUserUseCaseImpl(
    private val repository: AuthorizationRepository
) : RegisterUserUseCase {

    override fun invoke(registrationInfo: RegistrationInfo) {
        TODO("Should invoke repository and return correct status from server")
    }
}
