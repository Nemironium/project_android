package io.nemiron.meetgo.di

import io.nemiron.meetgo.data.repositories.AuthorizationRepositoryImpl
import org.koin.dsl.module

val registrationModule = module {
    single { AuthorizationRepositoryImpl(get()) }
}