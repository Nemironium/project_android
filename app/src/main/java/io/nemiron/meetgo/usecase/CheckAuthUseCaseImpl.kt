package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.managers.AuthorizationManager

class CheckAuthUseCaseImpl(
    private val authManager: AuthorizationManager
) : CheckAuthUseCase {
    override fun invoke(): Boolean =
        authManager.isUserLogged
}
