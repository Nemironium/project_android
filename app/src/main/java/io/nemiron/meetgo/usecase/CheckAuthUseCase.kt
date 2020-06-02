package io.nemiron.meetgo.usecase

import io.nemiron.meetgo.data.managers.AuthorizationManager

class CheckAuthUseCase(
    private val authManager: AuthorizationManager
)  {
    operator fun invoke(): Boolean =
        authManager.isUserLogged
}
