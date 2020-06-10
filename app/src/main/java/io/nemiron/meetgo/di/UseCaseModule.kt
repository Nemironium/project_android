package io.nemiron.meetgo.di

import io.nemiron.meetgo.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { CheckAuthUseCase(authManager = get()) }
    single { CheckFirstLaunchUseCase(authManager = get()) }
    single { ShowOnBoardingUseCase(authManager = get()) }
    single { LoginUseCase(repository = get()) }
    single { ValidateRegistrationDataUseCase() }
    single { RegisterUseCase(repository = get()) }
    single { TagsInteractor(repository = get()) }
}
