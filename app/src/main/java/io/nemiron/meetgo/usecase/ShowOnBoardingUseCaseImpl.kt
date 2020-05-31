package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.managers.AuthorizationManager

class ShowOnBoardingUseCaseImpl(
    private val authManager: AuthorizationManager
) : ShowOnBoardingUseCase {
    override fun invoke() {
        authManager.isFirstTimeLaunch = false
    }
}
