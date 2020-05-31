package io.nemiron.meetgo.usecase

import io.nemiron.domain.entities.LoginAnswer
import io.nemiron.domain.entities.LoginInfo

interface LoginUseCase {
    suspend operator fun invoke(info: LoginInfo): LoginAnswer
}
