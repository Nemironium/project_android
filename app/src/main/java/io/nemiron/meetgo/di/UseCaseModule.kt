package io.nemiron.meetgo.di

import io.nemiron.meetgo.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { CheckAuthUseCaseImpl(get()) as CheckAuthUseCase }
    single { CheckFirstLaunchUseCaseImpl(get()) as CheckFirstLaunchUseCase }
    single { ShowOnBoardingUseCaseImpl(get()) as ShowOnBoardingUseCase }
    single { LoginUseCaseImpl(get()) as LoginUseCase }
    single { ValidateRegistrationDataUseCaseImpl() as ValidateRegistrationDataUseCase }
    single { RegisterUseCaseImpl(get()) as RegisterUseCase }
}