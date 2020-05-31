package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.managers.AuthorizationManager

class CheckFirstLaunchUseCaseImpl(
    private val authManager: AuthorizationManager
) : CheckFirstLaunchUseCase {
    override fun invoke(): Boolean =
        authManager.isFirstTimeLaunch
}