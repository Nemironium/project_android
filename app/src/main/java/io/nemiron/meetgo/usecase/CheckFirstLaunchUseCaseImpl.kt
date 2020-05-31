package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.repositories.AuthorizationRepository

class CheckFirstLaunchUseCaseImpl(
    private val authorizationRepository: AuthorizationRepository
) : CheckFirstLaunchUseCase {
    override fun invoke(): Boolean =
        authorizationRepository.isFirstTimeLaunch
}
