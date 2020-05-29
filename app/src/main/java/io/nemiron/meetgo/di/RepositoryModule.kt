package io.nemiron.meetgo.di

import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import io.nemiron.meetgo.data.repositories.AuthorizationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthorizationRepositoryImpl(get()) as AuthorizationRepository }
}