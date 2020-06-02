package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.managers.AuthorizationManager

class ShowOnBoardingUseCase(
    private val authManager: AuthorizationManager
) {
    operator fun invoke() {
        authManager.isFirstTimeLaunch = false
    }
}
