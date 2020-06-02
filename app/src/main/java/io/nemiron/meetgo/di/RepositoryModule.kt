package io.nemiron.meetgo.di

import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthorizationRepository(authorizationClient = get(), authorizationManager = get()) }
}
