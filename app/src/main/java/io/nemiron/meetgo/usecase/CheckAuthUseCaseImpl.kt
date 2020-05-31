package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.repositories.AuthorizationRepository

class CheckAuthUseCaseImpl(
    private val authorizationRepository: AuthorizationRepository
) : CheckAuthUseCase {
    override fun invoke(): Boolean =
        authorizationRepository.isUserLogged
}
