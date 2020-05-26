package io.nemiron.meetgo.di

import io.nemiron.meetgo.usecase.RegisterUserUseCaseImpl
import io.nemiron.meetgo.usecase.ValidateRegistrationDataUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single { RegisterUserUseCaseImpl(get()) }
    single { ValidateRegistrationDataUseCaseImpl() }
}